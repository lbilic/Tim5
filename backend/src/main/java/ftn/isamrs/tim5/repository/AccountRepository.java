package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    Account findByActivationId(String activationId);
}
