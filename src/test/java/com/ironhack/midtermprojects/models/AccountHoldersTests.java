package com.ironhack.midtermprojects.models;
import static org.junit.jupiter.api.Assertions.*;

import models.AccountHolder;
import models.CheckingAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SpringBootTest
public class AccountHoldersTests {

    @Test
    void getAndSetAge() throws Exception {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setAge(30);
        assertEquals(accountHolder.getAge(), 30);
    }
}
