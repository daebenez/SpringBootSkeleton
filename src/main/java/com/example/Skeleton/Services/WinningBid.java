package com.example.Skeleton.Services;

import com.example.Skeleton.Model.Bids;
import com.example.Skeleton.controller.ProjectServiceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class WinningBid {
    Logger logger = LoggerFactory.getLogger(WinningBid.class);

    public Bids findWinner(List<Bids> releventBids) {
        if(releventBids == null || releventBids.size() == 0) {
            logger.info("No bids available to calculate winning bid");
            return null;
        }

        Collections.sort(releventBids, Comparator.comparingDouble(Bids::getBidPrice));
        return releventBids.get(0);
    }
}
