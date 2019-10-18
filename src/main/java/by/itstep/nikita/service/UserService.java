package by.itstep.nikita.service;

import by.itstep.nikita.domain.Roles;
import by.itstep.nikita.domain.User;
import by.itstep.nikita.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return user;
    }


    public boolean addUser(User user) {

        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {

            return false;

        } else {

            user.setActive(false);
            user.setRoles(Collections.singleton(Roles.USER));
            user.setActivationCode(UUID.randomUUID().toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepo.save(user);

            sendMessage(user);

            return true;
        }
    }


    private void sendMessage(User user) {

        if (!StringUtils.isEmpty(user.getEmail())) {

            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to GrodnoLift Web App. Please, visit next link:" + "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSenderService.send(user.getEmail(), "Activation Code!", message);
        }
    }


    public boolean activateUser(String code) {

        User userByCode = userRepo.findByActivationCode(code);

        if (userByCode == null) {

            return false;

        } else {

            userByCode.setActivationCode(null);
            userByCode.setActive(true);

            userRepo.save(userByCode);

            return true;
        }
    }


    public List<User> findAllUsers() {
        return userRepo.findAll(Sort.by("username"));
    }


    public void saveUser(User user, String username, Map<String, String> form) {

        user.setUsername(username);

        Set<String> roles = Arrays.stream(Roles.values())
                .map(Roles::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Roles.valueOf(key));
            }
        }

        userRepo.save(user);
    }


    public void updateProfile(User user, String password, String email, String filename) {
        String userEmail = user.getEmail();
        String userFilename = user.getFilename();
        boolean isEmailChanged = (email != null && email.equals(userEmail) || userEmail != null && userEmail.equals(email));

        boolean isFilenameChanged = (filename != null && filename.equals(userFilename) || userFilename != null && userFilename.equals(filename));
        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }

        if (!isFilenameChanged) {
            user.setFilename(filename);
        }

        userRepo.save(user);
        if (isEmailChanged) {
            sendMessage(user);
        }
    }
}
