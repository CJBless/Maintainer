package org.launchcode.maintainer.service;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.AppointmentType;
import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.models.Vehicle;
import org.launchcode.maintainer.service.data.AppointmentRepository;
import org.launchcode.maintainer.service.data.AppointmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class AppointmentTypeService {

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<AppointmentType> getAllAppointmentTypes(){
        return (List<AppointmentType>) appointmentTypeRepository.findAll();
    }

    public void addAppointmentType(AppointmentType type){
        appointmentTypeRepository.save(type);
    }

    public Optional<AppointmentType> getSingleAppointmentType(Integer id){
        return appointmentTypeRepository.findById(id);
    }

    public void updateAppointmentType(Integer id, AppointmentType type){
        appointmentTypeRepository.save(type);
    }

    public void deleteAppointmentType(Integer id){
        appointmentTypeRepository.deleteById(id);
    }

    public List<Appointment> getAllAppointments(){
        return (List<Appointment>) appointmentRepository.findAll();
    }

    public String getTypeString(Appointment appt) {
        Set<AppointmentType> types = appt.getAppointmentTypes();
        List<String> names = new ArrayList<>();
        String allTypes= "";
        for (AppointmentType type : types) {
            names.add(type.getName());
        }
        for(String name : names){
            if(names.get(names.size()-1).equals(name)){
                allTypes += name;
            } else {
                allTypes += name + ", ";
            }
        }
        return allTypes;
    }

}
