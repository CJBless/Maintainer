package org.launchcode.maintainer.service;


import org.launchcode.maintainer.models.Role;
import org.launchcode.maintainer.models.User;
import org.launchcode.maintainer.models.Vehicle;
import org.launchcode.maintainer.models.dto.RegisterDTO;
import org.launchcode.maintainer.service.data.RoleRepository;
import org.launchcode.maintainer.service.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerNewUserAccount(RegisterDTO user, Role role) {
        User newUser = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setRoles(roles);
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
    }

    public void validate(RegisterDTO user, Errors errors) {

        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.user.username");
        }

        if (user.getPassword().length() < 7) {
            errors.rejectValue("password", "Size.user.password");
        }

        if (!user.getPassword().matches(user.getVerifyPassword())) {
            errors.rejectValue("verifyPassword", "Diff.user.verifyPassword");
        }

        if(userRepository.findByEmail(user.getEmail()) != null) {
           errors.rejectValue("email","Duplicate.user.email");
        }

    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }




//    public User getUserFromSession(HttpSession session){
//        Integer userId = (Integer) session.getAttribute(userSessionKey);
//        if(userId == null) {
//            return null;
//        }
//
//        Optional<User> user = userRepository.findById(userId);
//
//        if(user.isEmpty()) {
//            return null;
//        }
//        return user.get();
//    }

//    private static void setUserInSession(HttpSession session, User user){
//        session.setAttribute(userSessionKey, user.getId());
//    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Optional<User> getSingleUser(Integer id){
        return userRepository.findById(id);
    }

    public void updateUser(Integer userId, User editUser){
        User user = userRepository.findById(userId).get();
        user.setRoles(editUser.getRoles());
        user.setVehicles(editUser.getVehicles());
        user.setName(editUser.getName());
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        Optional<User> optUser = userRepository.findById(userId);
        Set<User> userSet = new HashSet<>();
        Set<Vehicle> vehiclesList = new HashSet<>();
        optUser.ifPresent(user -> {
            userSet.add(user);
            vehiclesList.addAll(user.getVehicles());
        });
        for(Vehicle vehicle : vehiclesList){
            vehicle.removeUsers(userSet);
        }
        userRepository.deleteById(userId);
    }

    public String getUsersString(Vehicle vehicle) {
        Set<User> users = vehicle.getUsers();
        List<String> names = new ArrayList<>();
        String allUsers = "";
        for (User user : users) {
            names.add(user.getName());
        }
        for(String name : names){
            if(names.get(names.size()-1).equals(name)){
                allUsers += name;
            } else {
                allUsers += name + ", ";
            }
        }
        return allUsers;
    }

}
