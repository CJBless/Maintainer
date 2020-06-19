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
@RequestMapping("")
public class HomeController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("")
    public String dashboard(){
        return "index";
    }

}
