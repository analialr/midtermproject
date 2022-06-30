package models;

import classes.Money;
import enums.Status;
import models.Account;

import javax.persistence.*;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class StudentCheckingAccount extends Account {
    private String secretKey;
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentCheckingAccount() {
    }

    public StudentCheckingAccount(Money balance, Money penaltyFee, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Date creationDate, Status status) {
        super(balance, penaltyFee, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
