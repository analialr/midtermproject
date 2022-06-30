package services;

import classes.Money;
import enums.Status;
import models.Account;
import models.AccountHolder;
import models.CheckingAccount;
import models.StudentCheckingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CheckingAccountsRepository;
import repositories.StudentCheckingAccountsRepository;

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
