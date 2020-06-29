package org.launchcode.maintainer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotBlank(message = "This is a required field")
    private String title, location;

    private String backgroundColor;
    private String textColor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime end;

    @NotNull(message = "Must include at least one appointment type")
    @ManyToMany
    @JoinTable(name = "appointment_and_types",
            joinColumns = {
                @JoinColumn(name = "appt_id") }, inverseJoinColumns = {
                @JoinColumn(name = "type_id")
            })
    @JsonIgnoreProperties("appointments")
    private Set<AppointmentType> appointmentTypes = new HashSet<>();

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties({"id", "year", "make", "model", "apptColor",
                            "txtColor", "owners", "appointments"})
    private Vehicle vehicle;


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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Vehicle vehicle){
        this.backgroundColor = vehicle.getApptColor();
    }

    public String getTextColor(){
        return textColor;
    }

    public void setTextColor(Vehicle vehicle){
        this.textColor = vehicle.getTxtColor();
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime dateTime) {
        this.start = dateTime;
    }

    public String getDateTimeString() {
        return start.format(DateTimeFormatter.ofPattern("E MMM dd yyyy, hh:mm a"));
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime dateTimeEnd) {
        this.end = dateTimeEnd;
    }

    public Set<AppointmentType> getAppointmentTypes() {
        return appointmentTypes;
    }

    public void setAppointmentTypes(Set<AppointmentType> types){
        this.appointmentTypes = types;
    }

    public void removeAppointmentType(Set<AppointmentType> types){
        this.appointmentTypes.removeAll(types);
        for(AppointmentType type : types){
            type.removeAppointment(this);
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
                ", location='" + location + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", textColor='" + textColor + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", appointmentTypes=" + appointmentTypes +
                ", vehicle=" + vehicle.getName() +
                '}';
    }
}
