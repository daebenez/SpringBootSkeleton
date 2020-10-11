package com.example.Skeleton.controller;

import com.example.Skeleton.Model.Bids;
import com.example.Skeleton.Services.WinningBid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectServiceController {
    Logger logger = LoggerFactory.getLogger(ProjectServiceController.class);

    @Autowired
    WinningBid winningBid;

    private static List<Bids> allBids = new ArrayList<>();
    static {
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

        Bids bid3 = new Bids();
        bid3.setId(1);
        bid3.setBidOwner("Jane");
        bid3.setBidName("Mow your lawn - Jane");
        bid3.setProjectId(1);
        bid3.setBidDate("2020-10-09");
        bid3.setBidPrice(45);

        allBids.add(bid1);
        allBids.add(bid2);
        allBids.add(bid3);
    }

    @RequestMapping(value="/winningBid")
    public ResponseEntity<Object> getWinningBid(@RequestHeader int projectId,@RequestHeader String start,@RequestHeader String end) {
        logger.info("Getting winning bid for Project "+ projectId);
        try {
            List<Bids> bids = allBids;
            List<Bids> releventBids = new ArrayList<>();

            LocalDate projectStart = LocalDate.parse(start);
            LocalDate projectEnd = LocalDate.parse(end);
            for(Bids bid: allBids) {

                //default, ISO_LOCAL_DATE
                LocalDate bidDate = LocalDate.parse(bid.getBidDate());

                if(bid.getProjectId() == projectId && bidDate.isAfter(projectStart) && bidDate.isBefore(projectEnd)) {
                    releventBids.add(bid);
                }
            }

            Bids winner = winningBid.findWinner(releventBids);
            return new ResponseEntity<>(winner, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Unable to retrieve or calculate winning bid "+e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.EXPECTATION_FAILED);
        }
    }

}
