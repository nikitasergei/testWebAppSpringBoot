package by.itstep.nikita.repository;

import by.itstep.nikita.domain.TechServiceHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TechServiceHistoryRepo extends CrudRepository<TechServiceHistory, Long> {

    Page<TechServiceHistory> findAll(Pageable pageable);
}
