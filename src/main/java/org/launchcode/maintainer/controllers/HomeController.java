package org.launchcode.maintainer.controllers;

import org.launchcode.maintainer.models.Role;
import org.launchcode.maintainer.models.User;
import org.launchcode.maintainer.models.dto.RegisterDTO;
import org.launchcode.maintainer.service.RoleService;
import org.launchcode.maintainer.service.UserService;
import org.launchcode.maintainer.service.auth.SecurityService;
import org.launchcode.maintainer.service.data.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("")
public class HomeController {


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleService repo;


    @RequestMapping("/dashboard")
    public String home(){
        return "dashboard";
    }

    @GetMapping("/registration")
    public String displayRegistrationForm(Model model){
        model.addAttribute("user", new RegisterDTO());
        model.addAttribute("title", "Register");
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid RegisterDTO user, Errors errors, Model model) {
//        userService.validate(user, errors);

        Role role = new Role();
        role.setName(user.getRoles());
        repo.addRole(role);

        if(errors.hasErrors()){
            model.addAttribute("errors", errors);
            model.addAttribute("user", user);
            return "registration";
        }

        User existingUser = userService.findUserByEmail(user.getEmail());
        if (existingUser != null) {
            errors.rejectValue("email", "email.alreadyexists", "An account is already associated with that email address");
            model.addAttribute("title", "Register");
            return "registration";
        }
        String password = user.getPassword();
        String verifyPassword = user.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "registration";
        }

        userService.registerNewUserAccount(user, role);
        securityService.autoLogin(user.getEmail(), user.getPassword());
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and/or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

}
