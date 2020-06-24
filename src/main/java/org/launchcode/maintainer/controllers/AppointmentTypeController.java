package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.AppointmentType;
import org.launchcode.maintainer.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/appointments/types")
public class AppointmentTypeController {

    @Autowired
    private AppointmentTypeService apptTypeService;

    @GetMapping("")
    public String displayAllTypes(Model model){
        model.addAttribute("title", "All Appointment Types");
        model.addAttribute("types", apptTypeService.getAllAppointmentTypes());
        return "appointmentTypes/index";
    }

    @GetMapping("add")
    public String displayAddAppointmentTypeForm(Model model){
        model.addAttribute("type", new AppointmentType());
        return "appointmentTypes/add";
    }

    @PostMapping("add")
    public String processAddAppointmentType(@ModelAttribute @Valid AppointmentType newType,
                                            Errors errors, Model model){
        if(errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "appointments/add";
        }
        apptTypeService.addAppointmentType(newType);
        return "redirect:";
    }
}
