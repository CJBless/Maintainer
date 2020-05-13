package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("vehicles")
public class VehicleController {

    @GetMapping("add")
    public String displayAddVehicleForm(Model model) {
        model.addAttribute(new Vehicle());
        return "vehicles/add";
    }

    @PostMapping("add")
    public String processAddVehicleForm(@ModelAttribute @Valid Vehicle newVehicle,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "vehicles/add";
        }

        return "redirect:";
    }

    @GetMapping("view/{vehicleId}")
    public String displayViewVehicle(Model model, @PathVariable int vehicleId) {

        Optional optVehicle = null;
        if(optVehicle.isPresent()) {
            Vehicle vehicle = (Vehicle) optVehicle.get();
            model.addAttribute("vehicle", vehicle);
            return "vehicles/view";
        } else {
            return "redirect:../";
        }

    }

}
