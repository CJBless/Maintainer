package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class AppointmentType extends AbstractEntity{

    private static String longDescription;
    private static int recurrence;

    @ManyToMany
    @JoinColumn(name = "type_id")
    @JsonIgnore
    private Set<Appointment> appointments;


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


}
