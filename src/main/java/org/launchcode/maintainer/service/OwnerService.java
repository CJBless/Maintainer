package org.launchcode.maintainer.service;

import org.apache.tomcat.util.buf.StringUtils;
import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.models.Vehicle;
import org.launchcode.maintainer.service.data.OwnerRepository;
import org.launchcode.maintainer.service.data.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public void updateOwner(Integer ownerId, Owner editOwner){
        Owner owner = ownerRepository.findById(ownerId).get();
        owner.setRole(editOwner.getRole());
        owner.setVehicles(editOwner.getVehicles());
        owner.setName(editOwner.getName());
        ownerRepository.save(owner);
    }

    public void deleteOwner(Integer ownerId) {
        Optional<Owner> optOwner = ownerRepository.findById(ownerId);
        Set<Owner> ownerSet = new HashSet<>();
        Set<Vehicle> vehiclesList = new HashSet<>();
        optOwner.ifPresent(owner -> {
            ownerSet.add(owner);
            vehiclesList.addAll(owner.getVehicles());
        });
        for(Vehicle vehicle : vehiclesList){
            vehicle.removeOwners(ownerSet);
        }
        ownerRepository.deleteById(ownerId);
    }

    public String getOwnersString(Optional optVehicle) {
        Vehicle vehicle = (Vehicle) optVehicle.get();
        Set<Owner> owners = vehicle.getOwners();
        List<String> names = new ArrayList<>();
        String allOwners = "";
        for (Owner owner : owners) {
            names.add(owner.getName());
        }
        for(String name : names){
            if(names.get(names.size()-1).equals(name)){
                allOwners += name;
            } else {
                allOwners += name + ", ";
            }
        }
        return allOwners;
    }



}
