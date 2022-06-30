package com.ironhack.midtermprojects.services;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;
import com.ironhack.midtermprojects.models.Account;
import com.ironhack.midtermprojects.models.AccountHolder;
import com.ironhack.midtermprojects.models.CheckingAccount;
import com.ironhack.midtermprojects.models.StudentCheckingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ironhack.midtermprojects.repositories.CheckingAccountsRepository;
import com.ironhack.midtermprojects.repositories.StudentCheckingAccountsRepository;

import java.util.Date;

@Service
public class AccountsService {

    @Autowired
    private CheckingAccountsRepository checkingAccountsRepository;
    @Autowired
    private StudentCheckingAccountsRepository studentCheckingAccountsRepository;


    public Account createCheckingAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Date creationDate, Status status) {
        if(primaryOwner.getAge() >= CheckingAccount.MIN_AGE){
            CheckingAccount account = new CheckingAccount(balance, primaryOwner, secondaryOwner, secretKey, creationDate, status);
            checkingAccountsRepository.save(account);
            return account;
        }else {
            StudentCheckingAccount account = new StudentCheckingAccount(balance, primaryOwner, secondaryOwner, secretKey, creationDate, status);
            studentCheckingAccountsRepository.save(account);
            return account;
        }
    }
}
