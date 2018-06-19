package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Bid;
import ftn.isamrs.tim5.repository.BiddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Autowired
    private BiddingRepository biddingRepository;

    @Override
    public List<Bid> getMyBids(Long id) {
        return biddingRepository.getMyBids(id);
    }

    @Override
    public void save(Bid bid) {
        this.biddingRepository.save(bid);
    }

    @Override
    public Bid getBidById(Long id) {return biddingRepository.getBidById(id);}

    @Override
    public void deleteBids(Long id) { biddingRepository.delete(getBidById(id));}

    @Override
    public List<Bid> getMyBiddings(Long id) { return biddingRepository.getMyBiddings(id);}

    @Override
    public List<Bid> getBidsByPropId(Long id) {
        return biddingRepository.getBidsByPropId(id);
    }
}
