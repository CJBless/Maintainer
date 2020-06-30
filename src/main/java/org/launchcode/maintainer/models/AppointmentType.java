package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "appointment_type")
public class AppointmentType extends AbstractEntity{

    @NotBlank(message = "This is a required field")
    private String longDescription;

    private int recurrence;

    @ManyToMany(mappedBy="appointmentTypes")
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

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

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void removeAppointment(Appointment appointment){
        this.appointments.remove(appointment);
    }

}
