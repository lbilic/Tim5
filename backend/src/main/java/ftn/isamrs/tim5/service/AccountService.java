package ftn.isamrs.tim5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ftn.isamrs.tim5.model.User;

import java.util.List;

public interface AccountService {

    List<User> findAll();

    Page<User> findAll(Pageable page);

    User findOne(Long id);

    User findByUsername(String username);

    void checkUsername(String username);

    User save(User account);

    void remove(Long id);

    boolean isUsernameTaken(String username);
}
