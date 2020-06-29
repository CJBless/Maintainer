package org.launchcode.maintainer.service;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.models.Vehicle;
import org.launchcode.maintainer.service.data.AppointmentRepository;
import org.launchcode.maintainer.service.data.OwnerRepository;
import org.launchcode.maintainer.service.data.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public void updateVehicle(Integer vehicleId, Vehicle editVehicle){
        Optional<Vehicle> optVehicle = vehicleRepository.findById(vehicleId);
        if(optVehicle.isPresent()) {
            Vehicle vehicle = optVehicle.get();
            vehicle.setMake(editVehicle.getMake());
            vehicle.setModel(editVehicle.getModel());
            vehicle.setOwners(editVehicle.getOwners());
            vehicle.setYear(editVehicle.getYear());
            vehicle.setName(editVehicle.getName());
            vehicle.setApptColor(editVehicle.getApptColor());
            for (Appointment appt : vehicle.getAppointments()) {
                appt.setBackgroundColor(vehicle);
                appt.setTextColor(vehicle);
            }
            vehicleRepository.save(vehicle);
        }
    }

    public void deleteVehicle(Integer vehicleId){
            vehicleRepository.deleteById(vehicleId);
    }

    public Set<Appointment> findAllAppointments(Integer vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        return vehicle.getAppointments();
    }

    public String getVehiclesString(Owner owner) {
        Set<Vehicle> vehicles = owner.getVehicles();
        List<String> names = new ArrayList<>();
        String allVehicles = "";
        for (Vehicle vehicle : vehicles) {
            names.add(vehicle.getName() + ": " + vehicle.getMake() + " " +
                        vehicle.getModel());
        }
        for(String name : names){
            allVehicles += name + "\n";
        }
        return allVehicles;
    }


}

