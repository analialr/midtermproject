package com.ironhack.midtermprojects.models;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;
import com.ironhack.midtermprojects.repositories.CheckingAccountsRepository;
import com.ironhack.midtermprojects.services.AccountsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckingAccountTests {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private CheckingAccountsRepository checkingAccountsRepository;

    CheckingAccount checkingAccount = new CheckingAccount();

    @Test
    void applyPenaltyFee() throws Exception{
        BigDecimal new_balance = checkingAccount.getMinimum_balance().getAmount().subtract(new BigDecimal(50));
        checkingAccount.setBalance(new Money(new_balance));
        assertEquals(
                new_balance.subtract(checkingAccount.getPenaltyFee().getAmount()),
                checkingAccount.getBalance().getAmount());
    }

    @Test
    void dontApplyPenaltyFee() throws Exception{
        BigDecimal new_balance = checkingAccount.getMinimum_balance().getAmount().add(new BigDecimal(50));
        checkingAccount.setBalance(new Money(new_balance));
        assertEquals(
                new_balance,
                checkingAccount.getBalance().getAmount());
    }
}
