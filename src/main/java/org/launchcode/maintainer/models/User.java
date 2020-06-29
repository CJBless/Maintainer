package org.launchcode.maintainer.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User extends AbstractEntity {

//    @Email
    private String emailAddress;

    @NotBlank(message = "role must not be empty")
    private String role;

    @ManyToMany(mappedBy = "users")
    private final List<Vehicle> vehicles = new ArrayList<>();

    public User() {}

    public User(String role){
        super();
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle vehicle){
        this.vehicles.add(vehicle);
    }
}
