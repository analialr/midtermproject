package repositories;

import models.StudentCheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingAccountsRepository extends JpaRepository<StudentCheckingAccount, Long> {
}
