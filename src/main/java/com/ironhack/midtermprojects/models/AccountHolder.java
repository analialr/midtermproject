package com.ironhack.midtermprojects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class AccountHolder extends User {
    private Date birthDate;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> primaryAccountList = new ArrayList<>();


    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryAccountList;

    public AccountHolder() {
    }

    public AccountHolder(String name, String username, String password, Set<Role> roles, Date birthDate, List<Account> primaryAccountList, List<Account> secondaryAccountList) {
        super(name, username, password, roles);
        this.birthDate = birthDate;
        this.primaryAccountList = primaryAccountList;
        this.secondaryAccountList = secondaryAccountList;
    }

    public AccountHolder(String name, String username, String password, Set<Role> roles, Date birthDate, List<Account> primaryAccountList) {
        super(name, username, password, roles);
        this.birthDate = birthDate;
        this.primaryAccountList = primaryAccountList;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Account> getPrimaryAccountList() {
        return primaryAccountList;
    }

    public void setPrimaryAccountList(List<Account> primaryAccountList) {
        this.primaryAccountList = primaryAccountList;
    }

    public List<Account> getSecondaryAccountList() {
        return secondaryAccountList;
    }

    public void setSecondaryAccountList(List<Account> secondaryAccountList) {
        this.secondaryAccountList = secondaryAccountList;
    }

    public int getAge() {
        Date input = new Date();
        LocalDate date = this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(date, LocalDate.now()).getYears();
    }

    public void setAge(int years) {
        LocalDate localDate = LocalDate.now().minusYears(years);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        this.birthDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }
}
