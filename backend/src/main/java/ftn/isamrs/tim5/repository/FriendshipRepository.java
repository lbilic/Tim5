package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    //@Query(value = "SELECT * FROM Friendship f WHERE f.sender.name = :username", nativeQuery = true)
    //List<Friendship> findAllByUsername(@Param("username") String username);

    List<Friendship> findAllBySender(Account sender);
    List<Friendship> findAllByReceiver(Account receiver);
    Friendship findBySenderAndReceiver(Account sender, Account Receiver);

    Optional<Friendship> findById(Long id);
}
