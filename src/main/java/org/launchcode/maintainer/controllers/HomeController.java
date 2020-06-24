package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.service.data.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("")
    public String dashboard(){
        return "index";
    }

}
