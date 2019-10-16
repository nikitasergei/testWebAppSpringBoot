package by.itstep.nikita.services;

import by.itstep.nikita.domain.Lift;
import by.itstep.nikita.repositories.LiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LiftService {
    @Autowired
    LiftRepo liftRepo;

    public boolean saveLift(Lift lift) {
        liftRepo.save(lift);
        return true;
    }


    public Page<Lift> getAll(Pageable pageable) {
        return liftRepo.getAll(pageable);
    }

    public void remove(Lift removeLift) {
        removeLift.setDeleted(true);
        liftRepo.save(removeLift);
    }

    public void fixLift(Lift fixLift) {
        fixLift.setDeleted(false);
        liftRepo.save(fixLift);
    }
}
