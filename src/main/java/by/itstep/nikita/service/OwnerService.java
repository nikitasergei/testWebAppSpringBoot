package by.itstep.nikita.service;

import by.itstep.nikita.domain.Owner;
import by.itstep.nikita.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    OwnerRepo ownerRepo;

    /**
     * Method check is @param owner a new owner or existing owner and save @param owner depending on this
     *
     * @param owner - owner to save
     * @return true if @param owner was saved, else @return false
     */
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

    /**
     * @param pageable
     * @return owners as page
     */
    public Page<Owner> getAll(Pageable pageable) {
        return ownerRepo.findAll(pageable);
    }

    /**
     * @param pageable
     * @return owners as set
     */
    public Set<Owner> getOwners(Pageable pageable) {
        return getAll(pageable).stream().collect(Collectors.toSet());
    }

    /**
     * Set owner's parameter isDeleted as true
     *
     * @param owner - owner to remove
     */
    public void remove(Owner owner) {
        owner.setDeleted(true);
        ownerRepo.save(owner);
    }

    /**
     * Set owner's parameter isDeleted as false
     *
     * @param owner- owner to fix
     */
    public void fixOwner(Owner owner) {
        owner.setDeleted(false);
        ownerRepo.save(owner);
    }

    /**
     * @param name - owner's name
     * @return owner with @param name
     */
    public Owner searchByName(String name) {
        return ownerRepo.findByName(name);
    }

    /**
     * This method sort records from the table in accordance with @param indicator
     *
     * @param indicator - the parameter by which owners will be sorted
     * @param filter    - the parameter which mean that need to sort
     * @param pageable
     * @return filtered page of owners
     */
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

    /**
     * @param id - owner's id
     * @return owner with @param id
     */
    public Owner getById(Long id) {
        Optional<Owner> owner = ownerRepo.findById(id);
        return owner.orElse(null);
    }
}
