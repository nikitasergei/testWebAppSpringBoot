package by.itstep.nikita.repositories;

import by.itstep.nikita.domain.Lift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface LiftRepo extends CrudRepository<Lift, Long> {
    Lift findByRegNum(String regNum);

    Page<Lift> findAll(Pageable pageable);
}
