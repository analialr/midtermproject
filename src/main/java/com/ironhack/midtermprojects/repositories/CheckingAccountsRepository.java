package com.ironhack.midtermprojects.repositories;

import com.ironhack.midtermprojects.models.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountsRepository extends JpaRepository<CheckingAccount,Long> {


}
