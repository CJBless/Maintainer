package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.DisplayCalendar;
import org.launchcode.maintainer.models.data.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;

@Controller
public class HomeController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    private DisplayCalendar displayCalendar;

    @RequestMapping("")
    public String index(Model model) {

        LocalDateTime today = LocalDateTime.now();
        Month currentMonth = today.getMonth();
        Integer currentYear = today.getYear();

        model.addAttribute("title", "Dashboard");
//        model.addAttribute("cal", displayCalendar.getThisMonthData());
        model.addAttribute("today", today);
        return "index";
    }


}
