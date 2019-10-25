package by.itstep.nikita.service;

import by.itstep.nikita.domain.Lift;
import by.itstep.nikita.repository.LiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LiftService {
    @Autowired
    LiftRepo liftRepo;

    /**
     * This method check, is @param lift wrote in table in two ways: registration number and factory number, and try
     * to save it in one of two ways: with new ID or old ID
     *
     * @param lift
     * @return true if @param lift was saved
     */

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
            if (liftWRegNumFromDb == null || lift.getId().equals(liftWRegNumFromDb.getId())) {
                if (liftWFactNumFromDb == null || lift.getId().equals(liftWFactNumFromDb.getId())) {
                    liftRepo.save(lift);
                    return true;
                } else
                    return false;
            } else
                return false;
        }
    }

    /**
     * This method return all records from the table as a page of lifts
     *
     * @param pageable
     * @return page of lifts
     */
    public Page<Lift> getAll(Pageable pageable) {
        return liftRepo.findAll(pageable);
    }

    /**
     * If @param removeLift exist, set field isDelete of @param removeLift in true
     *
     * @param removeLift
     */
    public void remove(Lift removeLift) {
        removeLift.setDeleted(true);
        liftRepo.save(removeLift);
    }

    /**
     * If @param fixLift exist, set field isDelete of @param fixLift in false
     *
     * @param fixLift
     */
    public void fixLift(Lift fixLift) {
        fixLift.setDeleted(false);
        liftRepo.save(fixLift);
    }

    /**
     * This method sort records from the table in accordance with @param indicator
     *
     * @param indicator - the parameter by which lifts will be sorted
     * @param filter    - the parameter which mean that need to sort
     * @param pageable
     * @return filtered page of lifts
     */
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

    /**
     * @param id - id of the lift which we are looking for
     * @return lift with @param id
     */
    public Lift getById(Long id) {
        Optional<Lift> lift = liftRepo.findById(id);
        return lift.orElse(null);
    }

    public Page<Lift> getByOwnersId(Long id, Pageable pageable) {
        return liftRepo.findByOwnerId(id, pageable);
    }
}
