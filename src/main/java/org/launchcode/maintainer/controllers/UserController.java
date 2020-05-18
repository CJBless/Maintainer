package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.User;
import org.launchcode.maintainer.models.data.UserRepository;
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
    private UserRepository userRepository;

    @GetMapping
    public String displayUsers(Model model) {
        model.addAttribute("title", "All Users");
        model.addAttribute("users", userRepository.findAll());
        return "users/index";
    }

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        model.addAttribute("users", userRepository.findAll());
        return "users/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute @Valid User newUser,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "users/add";
        }
        userRepository.save(newUser);
        return "redirect:";
    }

    @GetMapping("view/{userId}")
    public String displayViewUser(Model model, @PathVariable Integer userId) {

        Optional<User> optUser = userRepository.findById(userId);
        if(optUser.isPresent()) {
            User user = optUser.get();
            model.addAttribute("user", user);
            return "users/view";
        } else {
            return "redirect:../";
        }

    }

}
