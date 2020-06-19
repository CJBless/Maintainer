package org.launchcode.maintainer.service;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.AppointmentType;
import org.launchcode.maintainer.models.data.AppointmentRepository;
import org.launchcode.maintainer.models.data.AppointmentTypeRepository;
import org.launchcode.maintainer.models.data.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Appointment> getAllAppointments(){
        return (List<Appointment>) appointmentRepository.findAll();
    }

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getSingleAppointment(Integer id){
        return appointmentRepository.findById(id);
    }

    public void updateAppointment(Integer id, Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Integer id){
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentType> getAllAppointmentTypes(){
        return (List<AppointmentType>) appointmentTypeRepository.findAll();
    }

}
