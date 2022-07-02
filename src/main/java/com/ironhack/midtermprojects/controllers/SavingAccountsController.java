package com.ironhack.midtermprojects.controllers;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.models.SavingAccount;
import com.ironhack.midtermprojects.repositories.SavingAccountsRepository;
import com.ironhack.midtermprojects.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SavingAccountsController {

    @Autowired
    SavingAccountsRepository savingAccountsRepository;

    @Autowired
    AccountsService accountsService;

    @GetMapping("/saving-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<SavingAccount> getAll() {
        return savingAccountsRepository.findAll();
    }

    @GetMapping("/saving-accounts/{account_id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalance(@PathVariable(name="account_id") Long account_id) {
        return accountsService.getSavingAccountBalance(account_id);
    }
}
