package ftn.isamrs.tim5;

import ftn.isamrs.tim5.dto.MovieScreeningCreateDTO;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.repository.BiddingRepository;
import ftn.isamrs.tim5.repository.MovieScreeningRepository;
import ftn.isamrs.tim5.service.BiddingService;
import ftn.isamrs.tim5.service.MovieScreeningService;
import ftn.isamrs.tim5.service.MovieScreeningServiceImpl;
import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BiddingTest {

    @MockBean
    private BiddingRepository biddingRepository;

    @Autowired
    private BiddingService biddingService;

    @Before
    public void setUp(){

        Bid bid = new Bid(new Props(), 200, new Account());

        List<Bid> bids = new ArrayList<>();
        bids.add(new Bid(new Props(), 200, new Account()));
        bids.add(new Bid(new Props(), 280, new Account()));
        bids.add(new Bid(new Props(), 320, new Account()));

        given(
                this.biddingRepository.getBidById(100L)
        ).willReturn(
                bid
        );

        given(
                this.biddingRepository.getMyBiddings(200L)
        ).willReturn(
                bids
        );

    }

    @Test
    public void testFindBid()
    {
       Bid bid =  this.biddingService.getBidById(100L);

        assertEquals(bid.getPrice(), 200);
    }

    @Test
    public void testFindBidsByMyId()
    {
        List<Bid> bids = this.biddingService.getMyBiddings(200L);

        assertEquals(3, bids.size());
        assertEquals(200, bids.get(0).getPrice());
        assertEquals(280, bids.get(1).getPrice());
        assertEquals(320, bids.get(2).getPrice());
    }

}
