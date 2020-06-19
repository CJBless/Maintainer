package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.models.data.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping
    public String displayUsers(Model model) {
        model.addAttribute("title", "All Users");
        model.addAttribute("users", ownerRepository.findAll());
        return "users/index";
    }

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new Owner());
        model.addAttribute("users", ownerRepository.findAll());
        return "users/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute @Valid Owner newOwner,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "users/add";
        }
        ownerRepository.save(newOwner);
        return "redirect:";
    }

    @GetMapping("view/{userId}")
    public String displayViewUser(Model model, @PathVariable Integer userId) {

        Optional<Owner> optUser = ownerRepository.findById(userId);
        if(optUser.isPresent()) {
            Owner owner = optUser.get();
            model.addAttribute("user", owner);
            return "users/view";
        } else {
            return "redirect:../";
        }

    }

}
