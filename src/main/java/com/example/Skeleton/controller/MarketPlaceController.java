package com.example.Skeleton.controller;

import com.example.Skeleton.Services.BidService;
import com.example.Skeleton.model.Projects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Skeleton.model.Bid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MarketPlaceController {
    Logger logger = LoggerFactory.getLogger(MarketPlaceController.class);

    @Autowired
    BidService bidService;

    private static List<Bid> bidList = new ArrayList<>();
    private static List<Projects> projectList = new ArrayList<>();
    static {
        Bid johnBid = new Bid(1L, 11L, "John-Lawn Mower", 101L,"2020-10-10", 30);
        Bid janBid = new Bid(2L,22L,"Jan-Lawn Mower",202L,"2020-10-13", 35);
        Bid NoahBid = new Bid(3L, 33L, "Noah-Lawn Mower",303L,"2020-10-14",25);
        bidList.add(johnBid);
        bidList.add(janBid);
        bidList.add(NoahBid);

        Projects project = new Projects(9L, "Lawn Mowing",999L,"","2020-10-09","2020-10-16");
        projectList.add(project);
    }

    @RequestMapping(value="/getWinningBid")
    public ResponseEntity<Object> getWinningBid(@RequestHeader long projectId) {
        logger.info("Calculating winning bid for project "+ projectId);
        try {
            Projects currentProject = null;
            for(Projects item: projectList) {
                if(projectId == item.getId()) {
                    currentProject = item;
                }
            }
            if(currentProject == null) {
                logger.error("This project does not exist in the database");
                return new ResponseEntity<>("No such project",HttpStatus.EXPECTATION_FAILED);
            }
            LocalDate start = LocalDate.parse(currentProject.getStartDate());
            LocalDate end = LocalDate.parse(currentProject.getEndDate());

            List<Bid> filteredBids = new ArrayList<>();

            for(Bid item: bidList) {
                LocalDate bDate = LocalDate.parse(item.getBidDate());
                if(start.isBefore(bDate) && end.isAfter(bDate)) {
                    filteredBids.add(item);
                }
            }
            if(filteredBids.size() == 0) {
                logger.info("No bids found for this project");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            Bid result = bidService.calculateWinningBid(filteredBids);
            return  new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            logger.error("Failed to calculate winning bid "+ e.getMessage());
            return new ResponseEntity<>("Exception thrown "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
