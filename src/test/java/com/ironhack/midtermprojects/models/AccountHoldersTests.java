package com.ironhack.midtermprojects.models;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountHoldersTests {

    @Test
    void getAndSetAge() throws Exception {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setAge(30);
        assertEquals(accountHolder.getAge(), 30);
    }
}
