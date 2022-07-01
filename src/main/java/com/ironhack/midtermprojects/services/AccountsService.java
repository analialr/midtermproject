package com.ironhack.midtermprojects.services;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;
import com.ironhack.midtermprojects.models.*;
import com.ironhack.midtermprojects.repositories.SavingAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ironhack.midtermprojects.repositories.CheckingAccountsRepository;
import com.ironhack.midtermprojects.repositories.StudentCheckingAccountsRepository;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class AccountsService {

    @Autowired
    private CheckingAccountsRepository checkingAccountsRepository;
    @Autowired
    private StudentCheckingAccountsRepository studentCheckingAccountsRepository;

    @Autowired
    private SavingAccountsRepository savingAccountsRepository;


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
