package org.launchcode.maintainer.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


//Like Job
@Entity
public class Vehicle extends AbstractEntity {

    private int year;

    private String make;

    private String model;

    @ManyToOne
    private User user;

    private String appointments;

    public Vehicle() {}

    public Vehicle(int aYear, String aMake, String aModel, User aUser, String someAppointments) {
        super();
        this.year = aYear;
        this.make = aMake;
        this.model = aModel;
        this.user = aUser;
        this.appointments = someAppointments;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User aUser) {
        this.user = aUser;
    }

    public String getAppointments() {
        return appointments;
    }

    public void setAppointments(String appointments) {
        this.appointments = appointments;
    }
}
