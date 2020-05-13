package org.launchcode.maintainer.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Appointment extends AbstractEntity {

    private Date date;

    private String location;

    @ManyToOne
    private Vehicle vehicle;

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

    public Vehicle getVehicle() {
        return vehicle;
    }
}
