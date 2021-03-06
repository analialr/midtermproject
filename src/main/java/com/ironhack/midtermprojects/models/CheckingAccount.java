package com.ironhack.midtermprojects.models;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class CheckingAccount extends Account{
    @Transient
    public static final int MIN_AGE = 24;

    @Embedded
    private static final Money monthlyMaintenanceFee = new Money(new BigDecimal(12));
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "minimum_balance_currency"))
    })
    private static final Money minimumBalance = new Money(new BigDecimal(250));
    private String secretKey;
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public CheckingAccount() {
    }

    public CheckingAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Date creationDate, Status status) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = status;
    }

    @Override
    public void setBalance(Money new_balance){
        if (new_balance.getAmount().compareTo(this.getMinimum_balance().getAmount()) < 0){
            new_balance.decreaseAmount(this.getPenaltyFee());
        }
        super.setBalance(new_balance);
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Money getMinimum_balance() {
        return minimumBalance;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
