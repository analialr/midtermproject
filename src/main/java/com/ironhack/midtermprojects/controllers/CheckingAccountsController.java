package com.ironhack.midtermprojects.controllers;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.models.CheckingAccount;
import com.ironhack.midtermprojects.repositories.CheckingAccountsRepository;
import com.ironhack.midtermprojects.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckingAccountsController {
    @Autowired
    CheckingAccountsRepository checkingAccountsRepository;

    @Autowired
    AccountsService accountsService;

    @GetMapping("/checking-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingAccount> getAll() {
        return checkingAccountsRepository.findAll();
    }

    @GetMapping("/checking-accounts/{account_id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalance(@PathVariable(name="account_id") Long account_id) {
        return accountsService.getCheckingAccountBalance(account_id);
    }

    @PatchMapping("/checking-accounts/{account_id}/balance/{balance}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBalance(@PathVariable(name="account_id") Long account_id, @PathVariable(name="balance") Integer balance) {
        accountsService.updateCheckingAccountBalance(account_id, balance);
    }
}
