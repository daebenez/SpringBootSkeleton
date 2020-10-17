package com.example.Skeleton.ServiceTest;

import com.example.Skeleton.Services.BidService;
import com.example.Skeleton.model.Bid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class BidServiceTest {

    @Test
    public void serviceTest() throws Exception {
        List<Bid> bidList = new ArrayList<>();
        Bid johnBid = new Bid(1L, 11L, "John-Lawn Mower", 101L,"2020-10-10", 30);
        Bid janBid = new Bid(2L,22L,"Jan-Lawn Mower",202L,"2020-10-13", 35);
        Bid NoahBid = new Bid(3L, 33L, "Noah-Lawn Mower",303L,"2020-10-14",25);
        bidList.add(johnBid);
        bidList.add(janBid);
        bidList.add(NoahBid);

        BidService bidService = new BidService();

        Bid res = bidService.calculateWinningBid(bidList);
        assertEquals(res,NoahBid);
    }

    @Test
    public void serviceNullTest() throws Exception {
        List<Bid> bidList = new ArrayList<>();
        BidService bidService = new BidService();
        Bid res = bidService.calculateWinningBid(bidList);
        assertEquals(res,null);
    }

}
