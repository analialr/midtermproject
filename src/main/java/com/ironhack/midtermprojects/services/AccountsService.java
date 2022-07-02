package com.ironhack.midtermprojects.services;

import com.ironhack.midtermprojects.classes.Money;
import com.ironhack.midtermprojects.enums.Status;
import com.ironhack.midtermprojects.models.*;
import com.ironhack.midtermprojects.repositories.CreditCardAccountsRepository;
import com.ironhack.midtermprojects.repositories.SavingAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ironhack.midtermprojects.repositories.CheckingAccountsRepository;
import com.ironhack.midtermprojects.repositories.StudentCheckingAccountsRepository;

import java.math.BigDecimal;
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
    @Autowired
    private CreditCardAccountsRepository creditCardAccountsRepository;


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


    public Money getSavingAccountBalance(Long account_id) {
        Optional<SavingAccount> account = savingAccountsRepository.findById(account_id);
        if(account.isPresent()){
            return account.get().getBalance();
        } else {
            return null;
        }
    }

    public Money getCheckingAccountBalance(Long account_id) {
        Optional<CheckingAccount> account = checkingAccountsRepository.findById(account_id);
        if(account.isPresent()){
            return account.get().getBalance();
        } else {
            return null;
        }
    }

    public Money getStudentCheckingAccountBalance(Long account_id) {
        Optional<StudentCheckingAccount> account = studentCheckingAccountsRepository.findById(account_id);
        if(account.isPresent()){
            return account.get().getBalance();
        } else {
            return null;
        }
    }


    public Money getCreditCardAccountBalance(Long account_id) {
        Optional<CreditCardAccount> account = creditCardAccountsRepository.findById(account_id);
        if(account.isPresent()){
            return account.get().getBalance();
        } else {
            return null;
        }
    }

    public Money updateStudentCheckingAccountBalance(Long account_id, Integer balance) {
        Optional<StudentCheckingAccount> account = studentCheckingAccountsRepository.findById(account_id);
        if(account.isPresent()){
            account.get().setBalance(new Money(new BigDecimal(balance)));
            studentCheckingAccountsRepository.save(account.get());
            return account.get().getBalance();
        } else {
            return null;
        }
    }

    public Money updateCheckingAccountBalance(Long account_id, Integer balance) {
        Optional<CheckingAccount> account = checkingAccountsRepository.findById(account_id);
        if(account.isPresent()){
            account.get().setBalance(new Money(new BigDecimal(balance)));
            checkingAccountsRepository.save(account.get());
            return account.get().getBalance();
        } else {
            return null;
        }
    }

    public Money updateSavingAccountBalance(Long account_id, Integer balance) {
        Optional<SavingAccount> account = savingAccountsRepository.findById(account_id);
        if(account.isPresent()){
            account.get().setBalance(new Money(new BigDecimal(balance)));
            savingAccountsRepository.save(account.get());
            return getSavingAccountBalance(account.get().getId());
        } else {
            return null;
        }
    }

    public Money updateCreditCardAccountBalance(Long account_id, Integer balance) {
        Optional<CreditCardAccount> account = creditCardAccountsRepository.findById(account_id);
        if(account.isPresent()){
            account.get().setBalance(new Money(new BigDecimal(balance)));
            creditCardAccountsRepository.save(account.get());
            return account.get().getBalance();
        } else {
            return null;
        }
    }

}
