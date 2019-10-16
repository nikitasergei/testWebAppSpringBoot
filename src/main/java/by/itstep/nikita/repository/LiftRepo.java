package by.itstep.nikita.repository;

import by.itstep.nikita.domain.Lift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface LiftRepo extends CrudRepository<Lift, Long> {
    Page<Lift> findById(Long id, Pageable pageable);

    Optional<Lift> findById(Long id);

    Lift findByRegNum(String regNum);

    Lift findByFactNum(String factNum);

    Page<Lift> findAll(Pageable pageable);

    Page<Lift> findByCity(String city, Pageable pageable);

    Page<Lift> findByAddress(String address, Pageable pageable);

    Page<Lift> findByActivationDate(String activationDate, Pageable pageable);

    Page<Lift> findByTo2Month(String to2Month, Pageable pageable);

    Page<Lift> findByPtoDate(String ptoDate, Pageable pageable);

    Page<Lift> findByRegNum(String regNum, Pageable pageable);

    Page<Lift> findByFactNum(String factNum, Pageable pageable);

    Page<Lift> findAllByOwnerId(Long id, Pageable pageable);
}
