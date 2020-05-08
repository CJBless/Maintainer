package org.launchcode.maintainer.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Appointment extends AbstractEntity {

    private Date date;

    private String location;

    private final List<Vehicle> vehicles = new ArrayList<>();

    public Appointment() {}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
