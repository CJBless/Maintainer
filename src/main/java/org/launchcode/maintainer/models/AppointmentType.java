package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "appointment_type")
public class AppointmentType extends AbstractEntity{

    private String longDescription;
    private int recurrence;

    @ManyToMany
    @JoinTable(name = "appointment_and_types",
            joinColumns = {
            @JoinColumn(name = "type_id") }, inverseJoinColumns = {
            @JoinColumn(name = "appt_id")
    })
    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentType(){}

    public AppointmentType(String aName, String aLongDescription, Integer aRecurrence) {
        super();
        this.setName(aName);
        this.longDescription = aLongDescription;
        this.recurrence = aRecurrence;

    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence = recurrence;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void removeAppointment(Appointment appointment){
        appointment.removeAppointmentType(this);
        this.appointments.remove(appointment);
    }

}
