package com.example.Skeleton.ServiceTest;

import com.example.Skeleton.Services.BidService;
import com.example.Skeleton.model.Bid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class BidServiceTest {

    @Test
    public void calculateWinningBidTest() throws Exception {
        BidService bidService = new BidService();
        Bid JaneBid = new Bid(1001L, 21L, 1L, "Jane-Lawn Mower", "2020-10-03", 40);
        Bid Alison = new Bid(1011L, 31L, 1L, "Alison Lawn Mower", "2020-10-04", 35);
        Bid Tara = new Bid(1022L, 41L, 1L,"Tara Lawn Mower", "2020-10-06", 45);
         List<Bid> bidList = new ArrayList<>();
         bidList.add(JaneBid);
         bidList.add(Alison);
         bidList.add(Tara);
         Bid winner = bidService.calculateWinningBid(bidList);
         assertEquals(winner, Alison);
    }
}
