package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Friendship;

import java.util.List;

public interface FriendshipService {
    Friendship save(Friendship friendship);
    List<Friendship> findAllBySender(String username);
    List<Friendship> findAllByReceiver(String username);
    Friendship findBySenderAndReceiver(String sender, String receiver);
    Boolean removeFriend(Long Id);
}
