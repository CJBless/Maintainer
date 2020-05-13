package org.launchcode.maintainer.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @OneToMany
    @JoinColumn(name="user_id")
    private final List<Vehicle> vehicles = new ArrayList<>();

    public User() {}


}
