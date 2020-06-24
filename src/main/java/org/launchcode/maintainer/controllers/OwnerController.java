package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public String displayUsers(Model model) {
        model.addAttribute("title", "All Users");
        model.addAttribute("owners", ownerService.getAllOwners());
        return "owners/index";
    }

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new Owner());
        return "owners/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute @Valid Owner newOwner,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "owners/add";
        }
        ownerService.addOwner(newOwner);
        return "redirect:";
    }

    @GetMapping("view/{userId}")
    public String displayViewUser(Model model, @PathVariable Integer userId) {

        Optional<Owner> optUser = ownerService.getSingleOwner(userId);
        if(optUser.isPresent()) {
            Owner owner = optUser.get();
            model.addAttribute("owner", owner);
            return "owners/view";
        } else {
            return "redirect:../";
        }

    }

}
