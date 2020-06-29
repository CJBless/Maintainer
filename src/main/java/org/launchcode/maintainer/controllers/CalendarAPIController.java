package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CalendarAPIController {

    @Autowired
    private AppointmentService apptService;

    @GetMapping("")
    public Iterable<Appointment> getAppointments(){
        return apptService.getAllAppointments();
    }


}
