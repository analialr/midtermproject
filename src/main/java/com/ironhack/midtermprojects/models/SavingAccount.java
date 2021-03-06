package com.ironhack.midtermprojects.models;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class SavingAccount extends Account {
    @Transient
    public static final BigDecimal MAXIMUM_INTEREST_RATE = new BigDecimal(0.5);
    @Transient
    public static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(0.0025);
    @Transient
    public static final Money MINIMUM_BALANCE = new Money(new BigDecimal(100));
    @Transient
    public static final Money DEFAULT_BALANCE = new Money(new BigDecimal(1000));

    private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance = DEFAULT_BALANCE;
    private Date creationDate = new Date();
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal interestRate = DEFAULT_INTEREST_RATE;

    private Date interestUpdatedAt;


    public SavingAccount() {

    }

    public SavingAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Money minimumBalance, Date creationDate, Status status, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.setMinimumBalance(minimumBalance);
        this.creationDate = creationDate;
        this.status = status;
        this.setInterestRate(interestRate);
    }

    public SavingAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Date creationDate, Status status, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = status;
        this.setInterestRate(interestRate);
    }

    @Override
    public void setBalance(Money new_balance){
        if (new_balance.getAmount().compareTo(this.getMinimumBalance().getAmount()) < 0){
            new_balance.decreaseAmount(this.getPenaltyFee());
        }
        super.setBalance(new_balance);
    }

    @Override
    public Money getBalance(){
        this.updateInterest();
        return super.getBalance();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = (minimumBalance.getAmount().compareTo(MINIMUM_BALANCE.getAmount()) < 0)
                ? MINIMUM_BALANCE
                : minimumBalance;
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

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = (interestRate.compareTo(MAXIMUM_INTEREST_RATE) > 0)
                ? MAXIMUM_INTEREST_RATE
                : interestRate;
    }

    public Date getInterestUpdatedAt() {
        return interestUpdatedAt;
    }

    public void setInterestUpdatedAt(Date interestUpdatedAt) {
        this.interestUpdatedAt = interestUpdatedAt;
    }

    public void updateInterest(){
        if(this.getInterestUpdatedAt() == null){
            this.interestUpdatedAt = this.creationDate;
        }
        LocalDate today = LocalDate.now();
        LocalDate lastUpdate = this.getInterestUpdatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int yearsSinceLastInterestUpdate = Period.between(lastUpdate, today).getYears();

        if(yearsSinceLastInterestUpdate >= 1) {
            BigDecimal interest = super.getBalance().getAmount().multiply(this.getInterestRate()).multiply(BigDecimal.valueOf(yearsSinceLastInterestUpdate));
            this.setBalance(new Money(super.getBalance().getAmount().add(interest)));
            this.setInterestUpdatedAt(new Date());
        }
    }
}
