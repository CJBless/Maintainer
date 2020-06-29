package org.launchcode.maintainer.controllers;

import javassist.NotFoundException;
import org.launchcode.maintainer.models.Owner;
import org.launchcode.maintainer.service.OwnerService;
import org.launchcode.maintainer.service.data.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("users")
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
        model.addAttribute("title", "Add User");
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
            model.addAttribute("entityId", owner.getId());
            model.addAttribute("entityName", owner.getName());
            model.addAttribute("link", "/users/view/");
            return "owners/view";
        } else {
            return "redirect:../";
        }

    }

    @RequestMapping("view/{userId}/delete")
    public String deleteUser(Model model, @PathVariable Integer userId,
                             RedirectAttributes redirectAttributes){
        ownerService.deleteOwner(userId);
        redirectAttributes.addFlashAttribute("message", "User has been deleted");
        return "redirect:../../";
    }


    @GetMapping("view/{userId}/edit")
    public String editUser(Model model, @PathVariable Integer userId){
        Optional<Owner> optUser = ownerService.getSingleOwner(userId);
        if(optUser.isPresent()){
            Owner owner = optUser.get();
            model.addAttribute("owner", owner);
            model.addAttribute("title", "Edit User");
            return "owners/add";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("view/{userId}/edit")
    public String processEditUser(@PathVariable Integer userId,
                                  @ModelAttribute @Valid Owner editedOwner,
                                  BindingResult result, Model model,
                                  RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("owner", editedOwner);
            model.addAttribute("title", "Edit User");
            model.addAttribute("errors", result);
            return "owners/add";
        }
        ownerService.updateOwner(userId, editedOwner);
        redirectAttributes.addFlashAttribute("message", "User has been edited");
        return "redirect:/users/view/{userId}";
    }
}
