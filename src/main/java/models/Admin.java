package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    public Admin(String name, String password, Set<Role> roles) {
        super(name, password, roles);
    }


}
