package com.ironhack.midtermprojects.repositories;

import com.ironhack.midtermprojects.models.CreditCardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardAccountsRepository  extends JpaRepository<CreditCardAccount,Long> {
}
