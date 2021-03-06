package com.ironhack.midtermprojects.models;

import com.ironhack.midtermprojects.classes.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class CreditCardAccount extends Account {
    @Transient
    public static final Money MAX_CREDIT_LIMIT = new Money(new BigDecimal(100000));
    @Transient
    public static final Money DEFAULT_CREDIT_LIMIT = new Money(new BigDecimal(100));
    @Transient
    public static final BigDecimal MAX_INTEREST_RATE = new BigDecimal(0.2);
    @Transient
    public static final BigDecimal MIN_INTEREST_RATE = new BigDecimal(0.1);
    @Transient
    public static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(0.2);

    private BigDecimal interestRate = DEFAULT_INTEREST_RATE;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "credit_limit_currency"))
    })
    private Money creditLimit = DEFAULT_CREDIT_LIMIT;
    private Date creationDate = new Date();
    private Date interestUpdatedAt;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CreditCardAccount() {
    }

    public CreditCardAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate, Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner);
        this.setInterestRate(interestRate);
        this.setCreditLimit(creditLimit);
    }
    public CreditCardAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        this.setInterestRate(interestRate);
    }

    public CreditCardAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner);
        this.setCreditLimit(creditLimit);
    }

    @Override
    public Money getBalance(){
        this.updateInterest();
        return super.getBalance();
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(MIN_INTEREST_RATE) < 0) {
            this.interestRate = MIN_INTEREST_RATE;
        } else if (interestRate.compareTo(MAX_INTEREST_RATE) > 0) {
            this.interestRate = MAX_INTEREST_RATE;
        } else {
            this.interestRate = interestRate;
        }
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = (creditLimit.getAmount().compareTo(MAX_CREDIT_LIMIT.getAmount()) > 0)
                ? MAX_CREDIT_LIMIT
                : creditLimit;
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

        int monthsSinceLastInterestUpdate = Period.between(lastUpdate, today).getMonths();

        if(monthsSinceLastInterestUpdate >= 1) {
            BigDecimal monthly_interest_rate = this.getInterestRate().multiply(new BigDecimal(0.12));
            BigDecimal interest = super.getBalance().getAmount().multiply(monthly_interest_rate.multiply(new BigDecimal(monthsSinceLastInterestUpdate)));
            BigDecimal new_balance = super.getBalance().increaseAmount(interest);
            this.setBalance(new Money(new_balance));
            this.setInterestUpdatedAt(new Date());
        }
    }
}
