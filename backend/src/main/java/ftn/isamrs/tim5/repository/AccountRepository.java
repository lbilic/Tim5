package ftn.isamrs.tim5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.isamrs.tim5.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

}
