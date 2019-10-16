package by.itstep.nikita.repository;

import by.itstep.nikita.domain.Lift;
import by.itstep.nikita.domain.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface OwnerRepo extends CrudRepository<Owner, Long> {
    Page<Owner> findAll(Pageable pageable);

    Owner findByName(String name);

    Page<Owner> findByName(String name, Pageable pageable);

    Page<Owner> findByAddress(String address, Pageable pageable);

    Optional<Owner> findById(Long id);
}
