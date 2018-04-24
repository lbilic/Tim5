package ftn.isamrs.tim5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.isamrs.tim5.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
