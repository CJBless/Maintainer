package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.data.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "Dashboard");
        model.addAttribute("appointments", appointmentRepository.findAll());
        return "index";
    }


}
