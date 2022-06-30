package repositories;

import models.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountsRepository extends JpaRepository<CheckingAccount,Long> {


}
