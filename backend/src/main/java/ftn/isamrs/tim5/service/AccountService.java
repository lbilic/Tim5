package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.MovieReservation;
import ftn.isamrs.tim5.model.ShowReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Page<Account> findAll(Pageable page);

    Account findOne(Long id);

    Account findByUsername(String username);

    Account findByActivationId(String activationId);

    void checkUsername(String username);

    Account save(Account account);

    void remove(Long id);

    boolean isUsernameTaken(String username);

    List<MovieReservation> findAllMovieReservations(long id);

    List<ShowReservation> findAllShowReservations(long id);
}
