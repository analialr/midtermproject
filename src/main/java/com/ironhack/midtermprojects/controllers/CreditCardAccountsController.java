package com.ironhack.midtermprojects.controllers;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.models.CreditCardAccount;
import com.ironhack.midtermprojects.repositories.CreditCardAccountsRepository;
import com.ironhack.midtermprojects.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditCardAccountsController {
    @Autowired
    CreditCardAccountsRepository creditCardAccountsRepository;

    @Autowired
    AccountsService accountsService;

    @GetMapping("/credit-card-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCardAccount> getAll() {
        return creditCardAccountsRepository.findAll();
    }

    @GetMapping("/credit-card-accounts/{account_id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalance(@PathVariable(name="account_id") Long account_id) {
        return accountsService.getCreditCardAccountBalance(account_id);
    }

    @PatchMapping("/credit-card-accounts/{account_id}/balance/{balance}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBalance(@PathVariable(name="account_id") Long account_id, @PathVariable(name="balance") Integer balance) {
        accountsService.updateCreditCardAccountBalance(account_id, balance);
    }
}
