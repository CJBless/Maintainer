package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.models.Vehicle;

import org.launchcode.maintainer.service.OwnerService;
import org.launchcode.maintainer.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public String displayAllVehicles(Model model) {
        model.addAttribute("title", "All Vehicles");
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "vehicles/index";
    }

    @GetMapping("add")
    public String displayAddVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("owners", ownerService.getAllOwners());
        model.addAttribute("title", "Add Vehicle");
        return "vehicles/add";
    }

    @PostMapping("add")
    public String processAddVehicleForm(@ModelAttribute @Valid Vehicle newVehicle,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("owners", ownerService.getAllOwners());
            return "vehicles/add";
        }
        vehicleService.addVehicle(newVehicle);
        return "redirect:";
    }

    @GetMapping("view/{vehicleId}")
    public String displayViewVehicle(Model model, @PathVariable Integer vehicleId) {

        Optional<Vehicle> optVehicle = vehicleService.getSingleVehicle(vehicleId);
        if(optVehicle.isPresent()) {
            Vehicle vehicle = optVehicle.get();
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("entityId", vehicle.getId());
            model.addAttribute("entityName", vehicle.getName());
            model.addAttribute("link", "/vehicles/view/");
            model.addAttribute("ownerString", ownerService.getOwnersString(vehicle));

            return "vehicles/view";
        } else {
            return "redirect:../";
        }

    }

    @RequestMapping("view/{vehicleId}/delete")
    public String deleteVehicle(Model model, @PathVariable Integer vehicleId,
                                RedirectAttributes redirectAttributes){
        vehicleService.deleteVehicle(vehicleId);
        redirectAttributes.addFlashAttribute("message", "Vehicle has been deleted");
        return "redirect:../../";
    }

    @GetMapping("view/{vehicleId}/edit")
    public String editVehicle(Model model, @PathVariable Integer vehicleId,
                              RedirectAttributes redirectAttributes){
        Optional<Vehicle> optVehicle = vehicleService.getSingleVehicle(vehicleId);
        if(optVehicle.isPresent()){
            Vehicle vehicle = optVehicle.get();
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("title", "Edit Vehicle");
            model.addAttribute("owners", ownerService.getAllOwners());
            return "vehicles/add";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("view/{vehicleId}/edit")
    public String processEditVehicle(@PathVariable Integer vehicleId,
                                     @ModelAttribute @Valid Vehicle editedVehicle,
                                     BindingResult result, Model model,
                                     RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("errors", result);
            model.addAttribute("vehicle", editedVehicle);
            model.addAttribute("title", "Edit Vehicle");
            model.addAttribute("owners", ownerService.getAllOwners());
            return "vehicles/add";
        }
        vehicleService.updateVehicle(vehicleId, editedVehicle);
        redirectAttributes.addFlashAttribute("message", "Vehicle has been edited");
        return "redirect:/vehicles/view/{vehicleId}";
    }


}
