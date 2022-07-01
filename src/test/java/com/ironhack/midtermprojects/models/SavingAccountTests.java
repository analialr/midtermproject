package com.ironhack.midtermprojects.models;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
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


    @Test
    void interestIsUpdatedOnGetBalanceAfterYear() {
        BigDecimal balance_before = new BigDecimal(1000000);

        SavingAccount savingAccount = new SavingAccount(
                new Money(balance_before),//Money balance
                new AccountHolder(),//, AccountHolder primaryOwner,
                null,// AccountHolder secondaryOwner,
                "secretKey",// String secretKey,
                getDateMinusYears(2),// Date creationDate,
                Status.ACTIVE,// Status status,
                SavingAccount.MAXIMUM_INTEREST_RATE// BigDecimal interestRate
        );

        BigDecimal balance_after = savingAccount.getBalance().getAmount();
        assertNotNull(savingAccount.getInterestUpdatedAt());
        assertTrue(balance_after.compareTo(balance_before) > 0);
    }


    private Date getDateMinusYears(int years){
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime newDateTime = dateTime.minusYears(years);
        return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
