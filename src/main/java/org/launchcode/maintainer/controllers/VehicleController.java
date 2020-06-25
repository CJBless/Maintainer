package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.models.Vehicle;
import org.launchcode.maintainer.service.OwnerService;
import org.launchcode.maintainer.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
        List<Owner> owners = ownerService.getAllOwners();
        model.addAttribute("owners", owners);
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

        Optional optVehicle = vehicleService.getSingleVehicle(vehicleId);
        if(optVehicle.isPresent()) {
            Vehicle vehicle = (Vehicle) optVehicle.get();
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("ownerString", ownerService.getOwnersString(optVehicle));
            return "vehicles/view";
        } else {
            return "redirect:../";
        }

    }

    @RequestMapping("view/{vehicleId}/delete")
    public String deleteVehicle(Model model, @PathVariable Integer vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        model.addAttribute("success", "Vehicle has been deleted");
        return "redirect:../../";
    }

//    @RequestMapping("view/{vehicleId}/edit")


}
