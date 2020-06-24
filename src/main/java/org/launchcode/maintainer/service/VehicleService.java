package org.launchcode.maintainer.service;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.models.Vehicle;
import org.launchcode.maintainer.service.data.AppointmentRepository;
import org.launchcode.maintainer.service.data.OwnerRepository;
import org.launchcode.maintainer.service.data.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public List<Vehicle> getAllVehicles(){
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    public void addVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getSingleVehicle(Integer id){
        return vehicleRepository.findById(id);
    }

    public void updateVehicle(Integer id, Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Integer id){

        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isPresent()){
            Vehicle vehicleObj = vehicle.get();
            List<Owner> owners = vehicleObj.getOwners();
            for(Owner owner : owners){
                owner.removeVehicle(vehicleObj);
            }
            vehicleRepository.deleteById(id);
        }

    }

//    public void deleteVehicle(Integer id){
//        vehicleRepository.deleteById(id);
//    }

    public List<Appointment> findAllAppointments(){
        return (List<Appointment>) appointmentRepository.findAll();
    }

    public List<Owner> getAllOwners() {
        return (List<Owner>) ownerRepository.findAll();
    }

}

