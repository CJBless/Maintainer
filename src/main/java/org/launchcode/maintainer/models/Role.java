package org.launchcode.maintainer.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role extends AbstractEntity {

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(){}

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
