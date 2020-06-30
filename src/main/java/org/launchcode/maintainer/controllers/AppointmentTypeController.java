package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Appointment;
import org.launchcode.maintainer.models.AppointmentType;
import org.launchcode.maintainer.service.AppointmentTypeService;
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
        model.addAttribute("title", "Add Appointment Type");
        return "appointmentTypes/add";
    }

    @PostMapping("add")
    public String processAddAppointmentType(@ModelAttribute @Valid AppointmentType newType,
                                            Errors errors, Model model){
        if(errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("title", "Add Appointment Type");
            return "appointmentTypes/add";
        }
        apptTypeService.addAppointmentType(newType);
        return "redirect:../add";
    }

    @GetMapping("view/{typeId}")
    public String displayViewTypes(Model model, @PathVariable Integer typeId){

        Optional<AppointmentType> optType = apptTypeService.getSingleAppointmentType(typeId);
        if(optType.isPresent()){
            AppointmentType type = optType.get();
            model.addAttribute("type", type);
            model.addAttribute("entityId", type.getId());
            model.addAttribute("entityName", type.getName());
            model.addAttribute("link", "/appointments/types/view/");
            return "appointmentTypes/view";
        } else {
            return "redirect:../";
        }

    }

    @RequestMapping("view/{typeId}/delete")
    public String deleteType(Model model, @PathVariable Integer typeId,
                                    RedirectAttributes redirectAttributes){
        apptTypeService.deleteAppointmentType(typeId);
        redirectAttributes.addFlashAttribute("message", "Appointment Type has been deleted");
        return "redirect:../../";
    }

    @GetMapping("view/{typeId}/edit")
    public String editType(Model model, @PathVariable Integer typeId,
                                  RedirectAttributes redirectAttributes){
        Optional<AppointmentType> optType = apptTypeService.getSingleAppointmentType(typeId);
        if(optType.isPresent()){
            AppointmentType type = optType.get();
            model.addAttribute("type", type);
            model.addAttribute("title", "Edit Appointment Type");
            return "appointmentTypes/add";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("view/{typeId}/edit")
    public String processEditType(@PathVariable Integer typeId,
                                  @ModelAttribute @Valid AppointmentType editedType,
                                  BindingResult result, Model model,
                                  RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute("errors", result);
            model.addAttribute("type", editedType);
            return "appointmentTypes/add";
        }
        apptTypeService.updateAppointmentType(typeId, editedType);
        attributes.addFlashAttribute("message", "Appointment type has been updated");
        return "redirect:/appointments/types/view/{typeId}";
    }

}
