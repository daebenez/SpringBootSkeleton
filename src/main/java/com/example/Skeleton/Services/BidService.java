package com.example.Skeleton.Services;

import com.example.Skeleton.controller.MarketPlaceController;
import com.example.Skeleton.model.Bid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class BidService {
    Logger logger = LoggerFactory.getLogger(BidService.class);

    public Bid calculateWinningBid(List<Bid> bidList) throws Exception {
        if(bidList.size() == 0) {
            logger.info("No bids to calculate winning bid from");
            return null;
        }
        PriorityQueue<Bid> minHeap = new PriorityQueue<>(Comparator.comparingDouble(Bid::getBidPrice));
        for(Bid item: bidList) {
            minHeap.offer(item);
        }
        return minHeap.poll();
    }
}
