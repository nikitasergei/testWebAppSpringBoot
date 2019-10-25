package by.itstep.nikita.repository;

import by.itstep.nikita.domain.Lift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface LiftRepo extends CrudRepository<Lift, Long> {

    Optional<Lift> findById(Long id);

    Lift findByRegNum(String regNum);

    Lift findByFactNum(String factNum);

    Page<Lift> findAll(Pageable pageable);

    Page<Lift> findByCity(String city, Pageable pageable);

    Page<Lift> findByAddress(String address, Pageable pageable);

    Page<Lift> findByActivationDate(String activationDate, Pageable pageable);

    Page<Lift> findByRegNum(String regNum, Pageable pageable);

    Page<Lift> findByFactNum(String factNum, Pageable pageable);

    Page<Lift> findByOwnerId(Long id, Pageable pageable);
}
