package org.launchcode.maintainer.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User extends AbstractEntity {

    @NotBlank(message = "role must not be empty")
    private String role;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Vehicle> vehicles = new ArrayList<>();

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
}
