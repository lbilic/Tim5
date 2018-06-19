package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Bid;

import java.util.List;

public interface BiddingService {

    List<Bid> getMyBids(Long id);

    void save(Bid bid);

    Bid getBidById(Long id);

    void deleteBids(Long id);

    List<Bid> getMyBiddings(Long id);

    List<Bid> getBidsByPropId(Long id);
}
