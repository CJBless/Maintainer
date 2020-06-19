package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Vehicle;
import org.launchcode.maintainer.models.data.OwnerRepository;
import org.launchcode.maintainer.models.data.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping
    public String displayAllVehicles(Model model) {
        model.addAttribute("title", "All Vehicles");
        model.addAttribute("vehicles",vehicleRepository.findAll());
        return "vehicles/index";
    }

    @GetMapping("add")
    public String displayAddVehicleForm(Model model) {
        model.addAttribute(new Vehicle());
        model.addAttribute("users", ownerRepository.findAll());
        return "vehicles/add";
    }

    @PostMapping("add")
    public String processAddVehicleForm(@ModelAttribute @Valid Vehicle newVehicle,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "vehicles/add";
        }
        vehicleRepository.save(newVehicle);
        return "redirect:";
    }

    @GetMapping("view/{vehicleId}")
    public String displayViewVehicle(Model model, @PathVariable Integer vehicleId) {

        Optional optVehicle = vehicleRepository.findById(vehicleId);
        if(optVehicle.isPresent()) {
            Vehicle vehicle = (Vehicle) optVehicle.get();
            model.addAttribute("vehicle", vehicle);
            return "vehicles/view";
        } else {
            return "redirect:../";
        }

    }

}
