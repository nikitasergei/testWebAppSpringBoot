package by.itstep.nikita.service;

import by.itstep.nikita.domain.Lift;
import by.itstep.nikita.domain.Owner;
import by.itstep.nikita.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OwnerService {
    @Autowired
    OwnerRepo ownerRepo;

    public boolean saveOwner(Owner owner) {
        Owner ownerFromDb = ownerRepo.findByName(owner.getName());
        if (owner.getId() == null) {
            if (ownerFromDb == null) {
                ownerRepo.save(owner);
                return true;
            } else {
                if (ownerFromDb == null || (ownerFromDb != null && owner.getId() == ownerFromDb.getId())) {
                    ownerRepo.save(owner);
                    return true;
                }
            }
        }
        return false;
    }

    public Page<Owner> getAll(Pageable pageable) {
        return ownerRepo.findAll(pageable);
    }

    public void remove(Owner owner) {
        owner.setDeleted(true);
        ownerRepo.save(owner);
    }

    public void fixOwner(Owner owner) {
        owner.setDeleted(false);
        ownerRepo.save(owner);
    }

    public Page<Owner> searchByName(String name, Pageable pageable) {
        return ownerRepo.findByName(name, pageable);
    }

    public Page<Owner> searchByAddress(String address, Pageable pageable) {
        return ownerRepo.findByAddress(address, pageable);
    }

    public Page<Owner> getByFilter(String indicator, String filter, Pageable pageable) {
        Page<Owner> page;
        switch (indicator) {
            case "name": {
                page = ownerRepo.findByName(filter, pageable);
                break;
            }
            case "address": {
                page = ownerRepo.findByAddress(filter, pageable);
                break;
            }
            default: {
                page = ownerRepo.findAll(pageable);
                break;
            }
        }
        return page;
    }

    public Owner getById(Long id) {
        Optional<Owner> owner = ownerRepo.findById(id);
        return owner.orElse(null);
    }
}
