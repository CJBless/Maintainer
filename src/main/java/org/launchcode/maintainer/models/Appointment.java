package org.launchcode.maintainer.models;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//Like Skills
@Entity
public class Appointment extends AbstractEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime dateTime;

    private String dateTimeString;

    @NotBlank
    private String location;

    @NotNull
    @ManyToOne
    private Vehicle vehicle;

    public Appointment() {}

    public Appointment(LocalDateTime aDateAndTime, String aLocation, Vehicle aVehicle) {
        super();
        this.dateTime = aDateAndTime;
        this.location = aLocation;
        this.vehicle = aVehicle;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTimeString() {
        dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("E MMM dd yyyy, hh:mm a"));
        return dateTimeString;
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

    //TODO insert hashcode, equals, to string


}
