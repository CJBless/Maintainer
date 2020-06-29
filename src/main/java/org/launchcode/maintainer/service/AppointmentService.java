package org.launchcode.maintainer.service;

import org.apache.tomcat.jni.Local;
import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.service.data.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Component
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments(){
        return (List<Appointment>) appointmentRepository.findAll();
    }

    public void addAppointment(Appointment appointment) {
        appointment.setBackgroundColor(appointment.getVehicle());
        appointment.setTextColor(appointment.getVehicle());
        appointmentRepository.save(appointment);
    }


    public Optional<Appointment> getSingleAppointment(Integer id){
        return appointmentRepository.findById(id);
    }

    public void updateAppointment(Integer apptId, Appointment editAppointment){
        Appointment appointment = appointmentRepository.findById(apptId).get();
        appointment.setTitle(editAppointment.getTitle());
        appointment.setStart(editAppointment.getStart());
        appointment.setEnd(editAppointment.getEnd());
        appointment.setLocation(editAppointment.getLocation());
        appointment.setAppointmentTypes(editAppointment.getAppointmentTypes());
        appointment.setVehicle(editAppointment.getVehicle());
        appointment.setBackgroundColor(editAppointment.getVehicle());
        appointment.setTextColor(editAppointment.getVehicle());
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Integer id){
        appointmentRepository.deleteById(id);
    }


}
