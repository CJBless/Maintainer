package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.User;
import org.launchcode.maintainer.service.UserService;
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
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String displayUsers(Model model) {
        model.addAttribute("title", "All Users");
        model.addAttribute("owners", userService.getAllUsers());
        return "owners/index";
    }

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Add User");
        return "owners/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute @Valid User newUser,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "owners/add";
        }
        userService.addUser(newUser);
        return "redirect:";
    }

    @GetMapping("view/{userId}")
    public String displayViewUser(Model model, @PathVariable Integer userId) {

        Optional<User> optUser = userService.getSingleUser(userId);
        if(optUser.isPresent()) {
            User user = optUser.get();
            model.addAttribute("owner", user);
            model.addAttribute("entityId", user.getId());
            model.addAttribute("entityName", user.getName());
            model.addAttribute("link", "/users/view/");
            return "owners/view";
        } else {
            return "redirect:../";
        }

    }

    @RequestMapping("view/{userId}/delete")
    public String deleteUser(Model model, @PathVariable Integer userId,
                             RedirectAttributes redirectAttributes){
        userService.deleteUser(userId);
        redirectAttributes.addFlashAttribute("message", "User has been deleted");
        return "redirect:../../";
    }


    @GetMapping("view/{userId}/edit")
    public String editUser(Model model, @PathVariable Integer userId){
        Optional<User> optUser = userService.getSingleUser(userId);
        if(optUser.isPresent()){
            User user = optUser.get();
            model.addAttribute("owner", user);
            model.addAttribute("title", "Edit User");
            return "owners/add";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("view/{userId}/edit")
    public String processEditUser(@PathVariable Integer userId,
                                  @ModelAttribute @Valid User editedUser,
                                  BindingResult result, Model model,
                                  RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("owner", editedUser);
            model.addAttribute("title", "Edit User");
            model.addAttribute("errors", result);
            return "owners/add";
        }
        userService.updateUser(userId, editedUser);
        redirectAttributes.addFlashAttribute("message", "User has been edited");
        return "redirect:/users/view/{userId}";
    }
}
