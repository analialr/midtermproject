package com.ironhack.midtermprojects.services;
import static org.junit.jupiter.api.Assertions.*;

import classes.Money;
import enums.Status;
import models.Account;
import models.AccountHolder;
import models.CheckingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import services.AccountsService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SpringBootTest
public class AccountsServiceTests {
    @Autowired
    private AccountsService accountsService;

    @Test
    void checkingAccountIsCreatedForUsersOverMinAge() {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setAge(CheckingAccount.MIN_AGE + 10);

        Account account = accountsService.createCheckingAccount(
                new Money(new BigDecimal(2.0)),//Money balance,
                new Money(new BigDecimal(2.0)),//Money penaltyFee,
                accountHolder,//AccountHolder primaryOwner,
                new AccountHolder(),//AccountHolder secondaryOwner,
                "secretKey",//String secretKey,
                new Date(),//Date creationDate,
                Status.ACTIVE//Status status
        );

        assertTrue(account.getPrimaryOwner().getAge() > CheckingAccount.MIN_AGE);
        assertEquals(account.getClass(), CheckingAccount.class);
    }



    /*
    @Test
    void studentCheckingAccountIsCreatedForUsersUnderMinAge() {

    }*/
}
