package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends AbstractEntity {

    @NotBlank(message = "role must not be empty")
    private String role;

    @ManyToMany
    @JoinTable(name = "vehicle_owner", joinColumns = {
            @JoinColumn(name = "owner_id") }, inverseJoinColumns = {
            @JoinColumn(name = "vehicle_id")
    })
    @JsonIgnore
    private List<Vehicle> vehicles = new ArrayList<>();

    public Owner() {}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles){
        this.vehicles = vehicles;
    }

    public void removeVehicle(Vehicle vehicle){
        vehicle.removeOwner(this);
        this.vehicles.remove(vehicle);
    }
}
