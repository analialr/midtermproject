package com.ironhack.midtermprojects.models;

import com.ironhack.midtermprojects.classes.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

@SpringBootTest
public class CreditCardAccountTests {

    @Test
    void tooHighCreditLimit() {
        CreditCardAccount creditCardAccount = new CreditCardAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                new BigDecimal(67.9999),
                new Money(CreditCardAccount.MAX_CREDIT_LIMIT.getAmount().add(new BigDecimal(1.00)))
        );

        assertEquals(creditCardAccount.getCreditLimit().getAmount(), CreditCardAccount.MAX_CREDIT_LIMIT.getAmount());
    }

    @Test
    void adequateCreditLimit() {
        CreditCardAccount creditCardAccount = new CreditCardAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                new BigDecimal(67.9999),
                CreditCardAccount.MAX_CREDIT_LIMIT
        );

        assertEquals(CreditCardAccount.MAX_CREDIT_LIMIT.getAmount(), creditCardAccount.getCreditLimit().getAmount());
    }

    @Test
    void defaultCreditLimit() {
        CreditCardAccount creditCardAccount = new CreditCardAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                new BigDecimal(67.9999)
        );

        assertEquals(creditCardAccount.getCreditLimit().getAmount(), CreditCardAccount.DEFAULT_CREDIT_LIMIT.getAmount());
    }

    @Test
    void defaultInterestRate() {
        CreditCardAccount creditCardAccount = new CreditCardAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                new Money(new BigDecimal(67.9999))
        );

        assertEquals(creditCardAccount.getInterestRate(), CreditCardAccount.DEFAULT_INTEREST_RATE);
    }

    @Test
    void tooHighInterestRate() {
        CreditCardAccount creditCardAccount = new CreditCardAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                CreditCardAccount.MAX_INTEREST_RATE.add(new BigDecimal(1.0)) // Too big interest rate
        );

        assertEquals(creditCardAccount.getInterestRate(), CreditCardAccount.MAX_INTEREST_RATE);
    }

    @Test
    void tooLowInterestRate() {
        CreditCardAccount creditCardAccount = new CreditCardAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                CreditCardAccount.MIN_INTEREST_RATE.subtract(new BigDecimal(0.1)) // Too low interest rate
        );

        assertEquals(creditCardAccount.getInterestRate(), CreditCardAccount.MIN_INTEREST_RATE);
    }

    @Test
    void adequateLowInterestRate() {
        BigDecimal sum = CreditCardAccount.MAX_INTEREST_RATE.add(CreditCardAccount.MIN_INTEREST_RATE);
        BigDecimal adequateInterestRate = sum.divide(new BigDecimal(2)); // average between max and min allowed values
        CreditCardAccount creditCardAccount = new CreditCardAccount(
                new Money(new BigDecimal(67.9999)),
                new AccountHolder(),
                new AccountHolder(),
                adequateInterestRate // adequate interest rate
        );

        assertEquals(creditCardAccount.getInterestRate(), adequateInterestRate);
    }
}
