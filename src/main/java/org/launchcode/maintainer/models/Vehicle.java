package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle extends AbstractEntity {

    @Size(max=20, message="Must not exceed 20 characters")
    @NotBlank(message = "Must not be blank")
    private String year, make, model, apptColor, txtColor;


    @NotEmpty(message = "Must include at least one owner")
    @ManyToMany
    @JoinTable(name = "vehicle_user", joinColumns = {
            @JoinColumn(name = "vehicle_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id")
    })
    @JsonIgnoreProperties("vehicles")
    private Set<User> users = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "vehicle_id")
    private Set<Appointment> appointments = new HashSet<>();


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

    public String getApptColor(){
        return apptColor;
    }

    public String getTxtColor(){
        return txtColor;
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

    public void setApptColor(String apptColor){
        this.apptColor = apptColor;
    }

    public void setTxtColor(String txtColor){
        this.txtColor = txtColor;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users){
        this.users = users;
    }

    public void removeUsers(Set<User> users){
        this.users.removeAll(users);
        for(User user : users){
            user.removeVehicle(this);
        }
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments){
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", apptColor='" + apptColor + '\'' +
                ", txtColor='" + txtColor + '\'' +
                ", owners=" + users +
                '}';
    }


}
