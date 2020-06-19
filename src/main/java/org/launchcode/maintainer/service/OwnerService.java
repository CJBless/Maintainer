package org.launchcode.maintainer.service;

import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.models.data.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public List<Owner> getAllOwners() {
        return (List<Owner>) ownerRepository.findAll();
    }

    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public Optional<Owner> getSingleOwner(Integer id){
        return ownerRepository.findById(id);
    }

    public void updateOwner(Integer id, Owner owner){
        ownerRepository.save(owner);
    }

    public void deleteOwner(Integer id) {
        ownerRepository.deleteById(id);
    }

}
