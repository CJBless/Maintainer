package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle extends AbstractEntity {

    @Size(max=20, message="Must not exceed 20 characters")
    @NotBlank(message = "Must not be blank")
    private String year, make, model;

    @NotEmpty(message = "Must include at least one owner")
    @ManyToMany
    @JoinTable(name = "vehicle_owner", joinColumns = {
            @JoinColumn(name = "vehicle_id") }, inverseJoinColumns = {
            @JoinColumn(name = "owner_id")
    })
    @JsonIgnoreProperties("vehicles")
    private Set<Owner> owners = new HashSet<>();

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

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners){
        this.owners = owners;
    }

    public void removeOwners(Set<Owner> owners){
        this.owners.removeAll(owners);
        for(Owner owner: owners){
            owner.removeVehicle(this);
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

}
