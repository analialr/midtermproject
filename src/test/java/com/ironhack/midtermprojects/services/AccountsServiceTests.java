package com.ironhack.midtermprojects.services;
import static org.junit.jupiter.api.Assertions.*;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;
import com.ironhack.midtermprojects.models.*;
import com.ironhack.midtermprojects.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
public class AccountsServiceTests {

    AccountHolder accountHolder = new AccountHolder();

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private CheckingAccountsRepository checkingAccountsRepository;
    @Autowired
    private StudentCheckingAccountsRepository studentCheckingAccountsRepository;
    @Autowired
    private SavingAccountsRepository savingAccountsRepository;

    @BeforeEach
    void setUp() {
        accountHolder = new AccountHolder("Analía", "analia", "password", null, new Date(), null, null);
        accountHolderRepository.save(accountHolder);
    }

    @AfterEach
    void tearDown() {
        checkingAccountsRepository.deleteAll();
        studentCheckingAccountsRepository.deleteAll();
        savingAccountsRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    void checkingAccountIsCreatedForUsersOverMinAge() {

        accountHolder.setAge(CheckingAccount.MIN_AGE + 10);

        Account account = accountsService.createCheckingAccount(
                new Money(new BigDecimal(2.0)),//Money balance,
                accountHolder,//AccountHolder primaryOwner,
                null,//AccountHolder secondaryOwner,
                "secretKey",//String secretKey,
                new Date(),//Date creationDate,
                Status.ACTIVE//Status status
        );

        assertTrue(account.getPrimaryOwner().getAge() > CheckingAccount.MIN_AGE);
        assertEquals(account.getClass(), CheckingAccount.class);
    }




    @Test
    void studentCheckingAccountIsCreatedForUsersUnderMinAge() {
        accountHolder.setAge(CheckingAccount.MIN_AGE - 10);

        Account account = accountsService.createCheckingAccount(
                new Money(new BigDecimal(2.0)),//Money balance,
                accountHolder,//AccountHolder primaryOwner,
                null,//AccountHolder secondaryOwner,
                "secretKey",//String secretKey,
                new Date(),//Date creationDate,
                Status.ACTIVE//Status status
        );

        assertTrue(account.getPrimaryOwner().getAge() < CheckingAccount.MIN_AGE);
        assertEquals(account.getClass(), StudentCheckingAccount.class);
    }

}
