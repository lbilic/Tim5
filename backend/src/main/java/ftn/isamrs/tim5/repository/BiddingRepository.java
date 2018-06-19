package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BiddingRepository extends JpaRepository<Bid, Long> {

//ovo se sad mora srediti jer vise nemas owner id :)
    @Query(value = "SELECT * FROM BIDS b where b.prop_id in (SELECT ID FROM PROPS p WHERE p.account_id = :ownerId)", nativeQuery = true)
    List<Bid> getMyBids(@Param("ownerId") Long id);

   // @Query(value = "SELECT * FROM BIDS b WHERE b.owner_id = :ownerId", nativeQuery = true)
    //List<Bid> getMyBids(@Param("ownerId") Long id);

    @Query(value = "SELECT * FROM BIDS b WHERE b.id = :bidId", nativeQuery = true)
    Bid getBidById(@Param("bidId") Long id);

    @Query(value = "DELETE * FROM BIDS b WHERE b.prop_id = :propId", nativeQuery = true)
    void deleteBids(@Param("propId") Long id);

    @Query(value = "SELECT * FROM BIDS b where b.bidder_id = :bidderId", nativeQuery = true)
    List<Bid> getMyBiddings(@Param("bidderId") Long id);

    @Query(value = "SELECT * FROM BIDS B WHERE b.prop_id = :propId", nativeQuery = true)
    List<Bid> getBidsByPropId(@Param("propId") Long id);

}
