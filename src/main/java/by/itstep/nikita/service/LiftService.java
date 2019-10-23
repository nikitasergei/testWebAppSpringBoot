package by.itstep.nikita.service;

import by.itstep.nikita.domain.Lift;
import by.itstep.nikita.domain.Owner;
import by.itstep.nikita.repository.LiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class LiftService {
    @Autowired
    LiftRepo liftRepo;

    public boolean saveLift(Lift lift) {
        Lift liftWRegNumFromDb = liftRepo.findByRegNum(lift.getRegNum());

        Lift liftWFactNumFromDb = liftRepo.findByFactNum(lift.getFactNum());

        if (lift.getId() == null) {
            if (liftWRegNumFromDb == null && liftWFactNumFromDb == null) {
                liftRepo.save(lift);
                return true;
            } else {
                return false;
            }
        } else {
            if (liftWRegNumFromDb == null || (liftWRegNumFromDb != null && lift.getId() == liftWRegNumFromDb.getId())) {
                if (liftWFactNumFromDb == null || (liftWFactNumFromDb != null && lift.getId() == liftWFactNumFromDb.getId())) {
                    liftRepo.save(lift);
                    return true;
                } else
                    return false;
            } else
                return false;
        }
    }

    public Page<Lift> getAll(Pageable pageable) {
        return liftRepo.findAll(pageable);
    }

    public void remove(Lift removeLift) {
        removeLift.setDeleted(true);
        liftRepo.save(removeLift);
    }

    public void fixLift(Lift fixLift) {
        fixLift.setDeleted(false);
        liftRepo.save(fixLift);
    }

    public Page<Lift> getByFilter(String indicator, String filter, Pageable pageable) {
        Page<Lift> page;
        switch (indicator) {
            case "city": {
                page = liftRepo.findByCity(filter, pageable);
                break;
            }
            case "address": {
                page = liftRepo.findByAddress(filter, pageable);
                break;
            }
            case "activationDate": {
                page = liftRepo.findByActivationDate(filter, pageable);
                break;
            }
            case "regNum": {
                page = liftRepo.findByRegNum(filter, pageable);
                break;
            }
            case "factNum": {
                page = liftRepo.findByFactNum(filter, pageable);
                break;
            }
            default:
                page = liftRepo.findAll(pageable);
                break;
        }
        return page;
    }

    public Lift getById(Long id) {
        Optional<Lift> lift = liftRepo.findById(id);
        return lift.orElse(null);
    }
}
