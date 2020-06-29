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
    @ManyToMany
    @JoinTable(
            name = "vehicle_users",
            joinColumns = @JoinColumn(name = "vehicles_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private final List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        for(User user : users){
            this.users.add(user);
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

}
