package com.ironhack.midtermprojects.controllers;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.models.SavingAccount;
import com.ironhack.midtermprojects.repositories.SavingAccountsRepository;
import com.ironhack.midtermprojects.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/saving-accounts/{account_id}/balance/{balance}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBalance(@PathVariable(name="account_id") Long account_id, @PathVariable(name="balance") Integer balance) {
        accountsService.updateSavingAccountBalance(account_id, balance);
    }
}
