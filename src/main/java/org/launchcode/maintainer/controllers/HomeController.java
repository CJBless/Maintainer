package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.data.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @RequestMapping("")
    public String index(Model model) {
        Calendar cal = Calendar.getInstance();
        model.addAttribute("appointments", appointmentRepository.findAll(Sort.by("dateTime").ascending()));
        return "index";
    }


}
