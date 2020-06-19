package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String title;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime start;

    private String dateTimeString;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime end;

    @NotBlank
    private String location;

    private String backgroundColor = "#FF0000";

    @ManyToMany
    private Set<AppointmentType> appointmentTypes = new HashSet<>(0);

    private static List<AppointmentType> typesList = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Vehicle vehicle;

    static {
        typesList.add(new AppointmentType("Oil change", "Tire rotation and oil and/or oil filter change", 3000));
        typesList.add(new AppointmentType("Replace tires", "Tire replacement", 0));
        typesList.add(new AppointmentType("Tire health", "Check tire tread/pressure", 1000));
        typesList.add(new AppointmentType("Battery", "Battery and cables check/replacement",3000));
        typesList.add(new AppointmentType("Fluid check", "Check/add: transmission/wiper/brake/power steering fluid", 3000));
        typesList.add(new AppointmentType("Wipers", "Check/replace wiper blades", 6000));
        typesList.add(new AppointmentType("Filters", "Check/replace engine air filter", 6000));
        typesList.add(new AppointmentType("12k maintenance", "Check/replace brakes/cabin air filter/fuel filter/coolant", 12000));
    }

    public Appointment() {}

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime dateTime) {
        this.start = dateTime;
    }

    public String getDateTimeString() {
        dateTimeString = start.format(DateTimeFormatter.ofPattern("E MMM dd yyyy, hh:mm a"));
        return dateTimeString;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime dateTimeEnd) {
        this.end = dateTimeEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<AppointmentType> getAppointmentTypes() {
        return appointmentTypes;
    }

    public void setAppointmentTypes(AppointmentType type){
        appointmentTypes.add(type);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor() {

        if(this.title.equals("Tire rotation and oil and/or oil filter change") || this.title.equals("Tire replacement")) {
            this.backgroundColor = "#FF0000";
        } else if(this.title.equals("Tire health: tread/pressure/rotation")){
            this.backgroundColor = "#20943F";
        } else if(this.title.equals("Battery and cables check/replacement")){
            this.backgroundColor = "#EE8211";
        } else if(this.title.equals("Fluid check and add: transmission, wiper, brake, power steering")){
            this.backgroundColor = "#EE8211";
        } else if (this.title.equals("Wiper Blades: check/replacement")){
            this.backgroundColor.equals("#20943F");
        } else if(this.title.equals("12k maintenance: check/replacement brakes/cabin air filter/fuel filter/coolant")){
            this.backgroundColor.equals("#FF0000");
        } else {
            this.backgroundColor.equals("#0275d8");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", dateTimeString='" + dateTimeString + '\'' +
                ", end=" + end +
                ", location='" + location + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
