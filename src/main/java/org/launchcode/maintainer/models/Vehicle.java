package org.launchcode.maintainer.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehicle extends AbstractEntity {

    private int year;

    private String make;

    private String model;

    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name = "vehicle_id")
    private final List<Appointment> appointments = new ArrayList<>();

    public Vehicle() {}

    public Vehicle(int aYear, String aMake, String aModel, User aUser) {
        super();
        this.year = aYear;
        this.make = aMake;
        this.model = aModel;
        this.user = aUser;
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

    public void setUser(User user) {
        this.user = user;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

//    public void setAppointments(List<Appointment> appointments) {
//        this.appointments = appointments;
//    }
}
