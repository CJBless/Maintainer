package org.launchcode.maintainer.service;

import org.launchcode.maintainer.models.AppointmentType;
import org.launchcode.maintainer.models.Role;
import org.launchcode.maintainer.service.data.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public List<Role> getAllRoles(){
        return (List<Role>) roleRepo.findAll();
    }

    public void addRole(Role role){
        roleRepo.save(role);
    }

}
