package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Owner extends AbstractEntity {

    @NotBlank(message = "Role must not be empty")
    private String role;

    @ManyToMany(mappedBy="owners")
//    @JoinTable(name = "vehicle_owner", joinColumns = {
//            @JoinColumn(name = "owner_id") }, inverseJoinColumns = {
//            @JoinColumn(name = "vehicle_id")
//    })
    @JsonIgnore
    private Set<Vehicle> vehicles = new HashSet<>();

    public Owner() {}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles){
        this.vehicles = vehicles;
    }

    public void removeVehicle(Vehicle vehicle){
        this.vehicles.remove(vehicle);
//        vehicle.removeOwner(this);
    }
}
