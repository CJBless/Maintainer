package org.launchcode.maintainer.service;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.AppointmentType;
import org.launchcode.maintainer.service.data.AppointmentRepository;
import org.launchcode.maintainer.service.data.AppointmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppointmentTypeService {

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    public List<AppointmentType> getAllAppointmentTypes(){
        List<AppointmentType> sorted = appointmentTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "recurrence"));
        return sorted;
    }

    public void addAppointmentType(AppointmentType type){
        appointmentTypeRepository.save(type);
    }

    public Optional<AppointmentType> getSingleAppointmentType(Integer id){
        return appointmentTypeRepository.findById(id);
    }

    public void updateAppointmentType(Integer typeId, AppointmentType editType){
        AppointmentType type = appointmentTypeRepository.findById(typeId).get();
        type.setName(editType.getName());
        type.setLongDescription(editType.getLongDescription());
        type.setRecurrence(editType.getRecurrence());
        type.setAppointments(editType.getAppointments());
        appointmentTypeRepository.save(type);
    }

    public void deleteAppointmentType(Integer id){

        appointmentTypeRepository.deleteById(id);
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
