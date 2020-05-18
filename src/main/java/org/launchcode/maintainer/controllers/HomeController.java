package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "Dashboard");
        return "index";
    }

//    @GetMapping("add")
//    public String displayAddAppointmentForm(Model model) {
//        model.addAttribute("title", "Schedule Maintenance");
//        model.addAttribute(new Appointment());
//        return "add";
//    }
//
//    @PostMapping("add")
//    public String processAddAppointmentForm(@ModelAttribute @Valid Appointment newAppointment,
//                                      Errors errors, Model model, @RequestParam int vehicleId) {
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Schedule Maintenance");
//            return "add";
//        }
//
//        return "redirect:";
//    }
//
//    @GetMapping("view/{appointmentId}")
//    public String displayViewAppointment(Model model, @PathVariable int appointmentId) {
//        return "view";
//    }

}
