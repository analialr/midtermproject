package com.ironhack.midtermprojects.models;

import classes.Money;
import enums.Status;
import models.AccountHolder;
import models.SavingAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class SavingAccountTests {

    @Test
    void tooBigInterestRate() throws Exception {
        SavingAccount savingAccount = new SavingAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                "secretKey",
                new Money(new BigDecimal(67.9999)),
                new Date(),
                Status.ACTIVE,
                SavingAccount.MAXIMUM_INTEREST_RATE.add(new BigDecimal(1.00))
        );

        assertEquals(savingAccount.getInterestRate(), SavingAccount.MAXIMUM_INTEREST_RATE);

    }

    @Test
    void adequateBigInterestRate() throws Exception {
        SavingAccount savingAccount = new SavingAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                "secretKey",
                new Money(new BigDecimal(67.9999)),
                new Date(),
                Status.ACTIVE,
                SavingAccount.MAXIMUM_INTEREST_RATE
        );

        assertEquals(savingAccount.getInterestRate(), SavingAccount.MAXIMUM_INTEREST_RATE);

    }

    @Test
    void tooLowMinimumBalance() throws Exception {
        SavingAccount savingAccount = new SavingAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                "secretKey",
                new Money(SavingAccount.MINIMUM_BALANCE.getAmount().subtract(new BigDecimal(1.00))),
                new Date(),
                Status.ACTIVE,
                new BigDecimal(0.5)
        );

        assertEquals(savingAccount.getMinimumBalance().getAmount(), SavingAccount.MINIMUM_BALANCE.getAmount());

    }

    @Test
    void adequateMinimumBalance() throws Exception {
        SavingAccount savingAccount = new SavingAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                "secretKey",
                SavingAccount.MINIMUM_BALANCE,
                new Date(),
                Status.ACTIVE,
                new BigDecimal(0.5)
        );

        assertEquals(savingAccount.getMinimumBalance().getAmount(), SavingAccount.MINIMUM_BALANCE.getAmount());

    }

    @Test
    void defaultMinimumBalance() throws Exception {
        SavingAccount savingAccount = new SavingAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                "secretKey",
                new Date(),
                Status.ACTIVE,
                new BigDecimal(0.5)
        );

        assertEquals(savingAccount.getMinimumBalance().getAmount(), SavingAccount.DEFAULT_BALANCE.getAmount());

    }


}
