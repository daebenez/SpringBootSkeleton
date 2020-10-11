package com.example.Skeleton;

import com.example.Skeleton.Model.Bids;
import com.example.Skeleton.Services.WinningBid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class WinningBidTest {

    @Test
    public void findWinnerTest() {
        Bids bid1 = new Bids();
        bid1.setId(1);
        bid1.setBidOwner("Paul");
        bid1.setBidName("Mow your lawn - Paul");
        bid1.setProjectId(1);
        bid1.setBidDate("2020-10-07");
        bid1.setBidPrice(50);

        Bids bid2 = new Bids();
        bid2.setId(1);
        bid2.setBidOwner("John");
        bid2.setBidName("Mow your lawn - John");
        bid2.setProjectId(1);
        bid2.setBidDate("2020-10-08");
        bid2.setBidPrice(40);

        List<Bids> allBids1 = new ArrayList<>();
        allBids1.add(bid1);
        allBids1.add(bid2);
        WinningBid winningBid = new WinningBid();

        Bids mockWinner = winningBid.findWinner(allBids1);
        assertEquals(mockWinner, bid2);
    }
}
