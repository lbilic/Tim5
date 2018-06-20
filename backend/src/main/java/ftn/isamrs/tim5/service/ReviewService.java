package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Review;

import java.util.List;

public interface ReviewService {

    boolean alreadyVoted(Long accountId, Long cineterId);

    Review save(Review review);

    List<Review> findByCineterId(Long cineterId);

    List<Review> findByShowId(Long id);
}
