package com.ironhack.midtermprojects.controllers;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.models.StudentCheckingAccount;
import com.ironhack.midtermprojects.repositories.StudentCheckingAccountsRepository;
import com.ironhack.midtermprojects.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentCheckingAccountsController {
    @Autowired
    StudentCheckingAccountsRepository studentCheckingAccountsRepository;

    @Autowired
    AccountsService accountsService;

    @GetMapping("/student-checking-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentCheckingAccount> getAll() {
        return studentCheckingAccountsRepository.findAll();
    }

    @GetMapping("/student-checking-accounts/{account_id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalance(@PathVariable(name="account_id") Long account_id) {
        return accountsService.getStudentCheckingAccountBalance(account_id);
    }

    @PatchMapping("/student-checking-accounts/{account_id}/balance/{balance}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBalance(@PathVariable(name="account_id") Long account_id, @PathVariable(name="balance") Integer balance) {
        accountsService.updateStudentCheckingAccountBalance(account_id, balance);
    }
}
