package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    Account findByActivationId(String activationId);

    @Query(value = "SELECT EMAIL FROM ACCOUNT A WHERE A.id = :accId", nativeQuery = true)
    String findEmailById(@Param("accId") Long id);


}
