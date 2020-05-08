package org.launchcode.maintainer.models;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class User extends AbstractEntity {

    private final List<Vehicle> vehicles = new ArrayList<>();

    public User() {}



}
