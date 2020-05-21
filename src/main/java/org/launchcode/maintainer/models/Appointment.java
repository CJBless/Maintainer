package org.launchcode.maintainer.models;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//Like Skills
@Entity
public class Appointment extends AbstractEntity {

    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    @NotNull
    private LocalDateTime date;

    @NotBlank
    private String location;

    @NotNull
    @ManyToOne
    private Vehicle vehicle;

    public Appointment() {}

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
