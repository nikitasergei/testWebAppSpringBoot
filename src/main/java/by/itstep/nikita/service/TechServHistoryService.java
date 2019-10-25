package by.itstep.nikita.service;

import by.itstep.nikita.domain.TechServiceHistory;
import by.itstep.nikita.repository.TechServiceHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TechServHistoryService {

    @Autowired
    TechServiceHistoryRepo techServiceHistoryRepo;

    /**
     * @param pageable
     * @return all TechServiceHistories as page
     */
    public Page<TechServiceHistory> getAll(Pageable pageable) {
        return techServiceHistoryRepo.findAll(pageable);
    }

    /**
     * @param techServiceHistory - techServiceHistory to save
     * @return true if @param techServiceHistory was saved
     */
    public boolean addTechServiceHistory(TechServiceHistory techServiceHistory) {
        techServiceHistoryRepo.save(techServiceHistory);
        return true;
    }

}
