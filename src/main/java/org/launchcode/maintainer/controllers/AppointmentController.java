package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.AppointmentType;
import org.launchcode.maintainer.service.AppointmentService;
import org.launchcode.maintainer.service.AppointmentTypeService;
import org.launchcode.maintainer.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService apptService;

    @Autowired
    private AppointmentTypeService apptTypeService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("")
    public String displayAllAppointments(Model model) {
        model.addAttribute("title", "All Appointments");
        model.addAttribute("appointments", apptService.getAllAppointments());
        return "appointments/index";
    }

    @GetMapping("add")
    public String displayAddAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("apptTypes", apptTypeService.getAllAppointmentTypes());
        return "appointments/add";
    }

    @PostMapping("add")
    public String processAddAppointmentForm(@ModelAttribute @Valid Appointment newAppointment,
                                            Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("apptTypes", apptTypeService.getAllAppointmentTypes());
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            return "appointments/add";
        }
//        model.addAttribute("appointment", apptService.getAllAppointments());
        apptService.addAppointment(newAppointment);
        return "redirect:";
    }

    @GetMapping("view/{appointmentId}")
    public String displayViewAppointment(Model model, @PathVariable Integer appointmentId) {

        Optional<Appointment> optAppt = apptService.getSingleAppointment(appointmentId);

        if(optAppt.isPresent()) {
            Appointment appointment = optAppt.get();
            model.addAttribute("appointment", appointment);
            model.addAttribute("apptRead", appointment.toString());
            return "appointments/view";
        } else {
            return "redirect:../";
        }

    }

}
