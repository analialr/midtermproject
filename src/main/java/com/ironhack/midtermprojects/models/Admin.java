package com.ironhack.midtermprojects.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String name, String username, String password, Set<Role> roles) {
        super(name, username, password, roles);
    }
}
