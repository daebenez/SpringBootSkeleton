package com.example.Skeleton.controller;

import com.example.Skeleton.Services.BidService;
import com.example.Skeleton.model.Bid;
import com.example.Skeleton.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {
    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    BidService bidService;

    private static List<Project> mockProjectDBCall = new ArrayList<>();
    private static List<Bid> mockBidCall = new ArrayList<>();
    static {

        Project johnProject = new Project(1L, 11L, "Lawn Mowing", "", "2020-10-01","2020-10-11");
        mockProjectDBCall.add(johnProject);

        Bid JaneBid = new Bid(1001L, 21L, 1L, "Jane-Lawn Mower", "2020-10-03", 40);
        Bid Alison = new Bid(1011L, 31L, 1L, "Alison Lawn Mower", "2020-10-04", 35);
        Bid Tara = new Bid(1022L, 41L, 1L,"Tara Lawn Mower", "2020-10-06", 45);
        mockBidCall.add(JaneBid);
        mockBidCall.add(Alison);
        mockBidCall.add(Tara);

    }

    @RequestMapping(value="/winningBid")
    public ResponseEntity<Object> getWinningBid(@RequestHeader long projectId) {
        logger.info("Getting winning bid");
        try {
            List<Project> allprojects = mockProjectDBCall;
            Project currentProject = null;
            for(Project item: allprojects) {
                if(item.getId() == projectId) {
                    currentProject = item;
                }
            }

            List<Bid> allBids = mockBidCall;
            List<Bid> filteredBids = new ArrayList<>();
            LocalDate start = LocalDate.parse(currentProject.getStartDate());
            LocalDate end = LocalDate.parse(currentProject.getEndDate());
            for(Bid item: allBids) {
                LocalDate date = LocalDate.parse(item.getBidDate());
                if(item.getProjectId() == currentProject.getId() && date.isAfter(start) && date.isBefore(end)) {
                    filteredBids.add(item);
                }
            }

            Bid winner = bidService.calculateWinningBid(filteredBids);
            if(winner == null) {
                return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);

            }
            return new ResponseEntity<>(winner, HttpStatus.OK);

        } catch(Exception e) {
            logger.error("Calculation of winning bid failed", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

    }


    @RequestMapping(value="/createBid", method = RequestMethod.POST)
    public ResponseEntity<Object> createBid(@RequestBody Bid bid) {
       try {
            mockBidCall.add(bid);
            return new ResponseEntity<>("Add success", HttpStatus.OK);
       } catch(Exception e) {
           return new ResponseEntity<>("Bid creation failed", HttpStatus.EXPECTATION_FAILED);
       }
    }

    @RequestMapping(value="/getBids")
    public ResponseEntity<Object> getBids(@RequestHeader long projectId) {
        logger.info("Getting all bids for project" + projectId);
        try {
            List<Bid> filteredList = new ArrayList<>();
            for(Bid item: mockBidCall) {
                if(item.getProjectId() == projectId) {
                    filteredList.add(item);
                }
            }
            return new ResponseEntity<>(filteredList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Getting bids failed");
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

}
