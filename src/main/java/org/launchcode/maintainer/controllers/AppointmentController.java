package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.data.AppointmentRepository;
import org.launchcode.maintainer.models.data.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public String displayAllAppointments(Model model) {
        model.addAttribute("title", "All Appointments");
        model.addAttribute("appointments",appointmentRepository.findAll());
        return "appointments/index";
    }

    @GetMapping("add")
    public String displayAddAppointmentForm(Model model) {
        model.addAttribute(new Appointment());
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "appointments/add";
    }

    @PostMapping("add")
    public String processAddAppointmentForm(@ModelAttribute @Valid Appointment newAppointment,
                                            Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "appointments/add";
        }
        appointmentRepository.save(newAppointment);
        return "redirect:";
    }

    @GetMapping("view/{appointmentId}")
    public String displayViewAppointment(Model model, @PathVariable Integer appointmentId) {

        Optional<Appointment> optAppt = appointmentRepository.findById(appointmentId);

        if(optAppt.isPresent()) {
            Appointment appointment = optAppt.get();
            model.addAttribute("appointment", appointment);
            return "appointments/view";
        } else {
            return "redirect:../";
        }

    }

}
