package com.ironhack.midtermprojects.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;
import com.ironhack.midtermprojects.models.AccountHolder;
import com.ironhack.midtermprojects.models.Role;
import com.ironhack.midtermprojects.models.SavingAccount;
import com.ironhack.midtermprojects.models.User;
import com.ironhack.midtermprojects.repositories.SavingAccountsRepository;
import com.ironhack.midtermprojects.repositories.UsersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.http.HttpHeaders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SavingAccountsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SavingAccountsRepository savingAccountsRepository;

    private User accountHolder, admin;
    private Role adminRole, accountHolderRole;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        accountHolder = new User("account holder", passwordEncoder.encode("123456"));
        accountHolderRole = new Role("ACCOUNT-HOLDER", accountHolder);
        accountHolder.setRoles(Set.of(accountHolderRole));

        admin = new User("admin", passwordEncoder.encode("123456"));
        adminRole = new Role("ADMIN", admin);
        admin.setRoles(Set.of(adminRole));

        usersRepository.saveAll(List.of(accountHolder, admin));
    }

    @AfterEach
    void tearDown() {
        usersRepository.deleteAll();
    }

    @Test
    void getAllWithoutAuth() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        MvcResult mvcResult = mockMvc.perform(get("/saving-accounts").headers(httpHeaders))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    void getAllAsAdmin() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        MvcResult mvcResult = mockMvc.perform(get("/saving-accounts").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"))
                .andReturn();
        //System.out.println("-----------------");
        //System.out.println("-----------------"+mvcResult.getResponse().getContentAsString());
        //System.out.println("-----------------");
    }


    @Test
    void getBalanceAsAdmin() throws Exception {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(new Money(new BigDecimal(1000000)));
        savingAccountsRepository.save(savingAccount);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        MvcResult mvcResult = mockMvc.perform(get("/saving-accounts/{id}/balance", savingAccount.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000000"));
    }

}
