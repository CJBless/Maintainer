package org.launchcode.maintainer.models.dto;

import org.launchcode.maintainer.models.Role;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class LoginDTO {

    @NotBlank(message="Required field")
    private String name;

    @NotBlank(message="Required field")
    private String username;

    @Email
    @NotBlank(message="Required field")
    private String email;

    @NotBlank(message="Required field")
    private String password;

    @NotEmpty(message = "Role can not be empty")
    private String roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
