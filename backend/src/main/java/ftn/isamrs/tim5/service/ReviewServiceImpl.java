package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public boolean alreadyVoted(Long accountId, Long cineterId) {

        return this.reviewRepository.alreadyVoted(accountId, cineterId) == null;
    }
}
