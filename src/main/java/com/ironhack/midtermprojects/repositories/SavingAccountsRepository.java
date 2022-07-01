package com.ironhack.midtermprojects.repositories;

import com.ironhack.midtermprojects.models.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountsRepository extends JpaRepository<SavingAccount,Long> {
}
