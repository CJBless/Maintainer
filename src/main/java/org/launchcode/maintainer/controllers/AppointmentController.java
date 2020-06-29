package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.AppointmentType;
import org.launchcode.maintainer.models.Vehicle;
import org.launchcode.maintainer.service.AppointmentService;
import org.launchcode.maintainer.service.AppointmentTypeService;
import org.launchcode.maintainer.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("title", "Add Appointment");
        return "appointments/add";
    }

    @PostMapping("add")
    public String processAddAppointmentForm(@ModelAttribute @Valid Appointment newAppointment,
                                            Errors errors, Model model) {

        if(newAppointment.getStart().isAfter(newAppointment.getEnd())){
            errors.rejectValue("end", "invalid.date", "End date/time must be after start date/time");
        }
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("apptTypes", apptTypeService.getAllAppointmentTypes());
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            model.addAttribute("title", "Add Appointment");
            return "appointments/add";
        }
        apptService.addAppointment(newAppointment);
        return "redirect:";
    }

    @GetMapping("view/{appointmentId}")
    public String displayViewAppointment(Model model, @PathVariable Integer appointmentId) {

        Optional<Appointment> optAppt = apptService.getSingleAppointment(appointmentId);
        if(optAppt.isPresent()) {
            Appointment appointment = optAppt.get();
            model.addAttribute("appointment", appointment);
            model.addAttribute("apptTypes", apptTypeService.getTypeString(appointment));
            model.addAttribute("entityId", appointment.getId());
            model.addAttribute("entityName", appointment.getTitle());
            model.addAttribute("link", "/appointments/view/");
            return "appointments/view";
        } else {
            return "redirect:../";
        }

    }

    @RequestMapping("view/{apptId}/delete")
    public String deleteAppointment(Model model, @PathVariable Integer apptId,
                                RedirectAttributes redirectAttributes){
        apptService.deleteAppointment(apptId);
        redirectAttributes.addFlashAttribute("message", "Appointment has been deleted");
        return "redirect:../../";
    }

    @GetMapping("view/{apptId}/edit")
    public String editAppointment(Model model, @PathVariable Integer apptId,
                                    RedirectAttributes redirectAttributes){
        Optional<Appointment> optAppt = apptService.getSingleAppointment(apptId);
        if(optAppt.isPresent()){
            Appointment appt = optAppt.get();
            model.addAttribute("appointment", appt);
            model.addAttribute("title", "Edit Appointment");
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            model.addAttribute("apptTypes", apptTypeService.getAllAppointmentTypes());
            return "appointments/add";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("view/{apptId}/edit")
    public String processEditVehicle(@PathVariable Integer apptId,
                                     @ModelAttribute @Valid Appointment editedAppt,
                                     BindingResult result, Model model,
                                     RedirectAttributes redirectAttributes){

        if(editedAppt.getStart().isAfter(editedAppt.getEnd())){
            result.rejectValue("end", "invalid.date", "End date/time must be after start date/time");
        }

        if(result.hasErrors()){
            model.addAttribute("errors", result);
            model.addAttribute("appointment", editedAppt);
            model.addAttribute("title", "Edit Appointment");
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            model.addAttribute("apptTypes", apptTypeService.getAllAppointmentTypes());
            return "appointments/add";
        }
        apptService.updateAppointment(apptId, editedAppt);
        redirectAttributes.addFlashAttribute("message", "Appointment has been edited");
        return "redirect:/appointments/view/{apptId}";
    }

}
