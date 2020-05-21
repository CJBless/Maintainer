package org.launchcode.maintainer.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


//Like Job
@Entity
public class Vehicle extends AbstractEntity {

    @NotBlank
    private String year;

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @NotNull
    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name = "vehicle_id")
    private List<Appointment> appointments = new ArrayList<>();

    public Vehicle() {}

    public String getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User aUser) {
        this.user = aUser;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

}
