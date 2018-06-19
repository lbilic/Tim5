package ftn.isamrs.tim5.service;

import org.springframework.stereotype.Service;

public interface ReviewService {

    public boolean alreadyVoted(Long accountId, Long cineterId);
}
