package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThirdParty{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String hashed_key;


    public ThirdParty() {
    }

    public ThirdParty(String name, String hashed_key) {
        this.name = name;
        this.hashed_key = hashed_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashed_key() {
        return hashed_key;
    }

    public void setHashed_key(String hashed_key) {
        this.hashed_key = hashed_key;
    }

    public Long getId() {
        return id;
    }
}
