package com.example.Skeleton.Controller;

import com.example.Skeleton.Dao.Bids;
import com.example.Skeleton.Dao.Project;
import com.example.Skeleton.Services.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProjectController {
    Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    private static Map<String, Project> projectDB = new HashMap<>();
    private static List<Bids> bidDB = new ArrayList<>();
    static {
        Bids bid1 = new Bids();
        bid1.setId(11);
        bid1.setProjectId(2);
        bid1.setBidName("Table 1");
        bid1.setBidOwner("Paul");
        bid1.setBidDate("2020-10-08");
        bid1.setPrice(20);

        Bids bid2 = new Bids();
        bid2.setId(12);
        bid2.setProjectId(2);
        bid2.setBidName("Table 2");
        bid2.setBidOwner("Joseph");
        bid2.setBidDate("2020-10-09");
        bid2.setPrice(15);

        Bids bid3 = new Bids();
        bid3.setId(13);
        bid3.setProjectId(1);
        bid3.setBidName("Lawn 1");
        bid3.setBidOwner("karl");
        bid3.setBidDate("2020-10-10");
        bid3.setPrice(1);

        bidDB.add(bid1);
        bidDB.add(bid2);
        bidDB.add(bid3);
    }

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(value="/projects", method = RequestMethod.POST)
    public ResponseEntity<Object> createProject(@RequestBody Project item) {
        try {
            projectRepository.save(item);
        } catch(Exception e) {
            logger.error("Unable to save to database "+ e.getMessage());
        }
        return new ResponseEntity<>("Add success", HttpStatus.OK);
    }

    @RequestMapping(value="/projects/winningBid")
    public ResponseEntity<Object> getWinningBid(@RequestHeader int projectId) {
        logger.info("Getting winning bid");
       try {
           List<Bids> projectBids = new ArrayList<>();
           for(Bids bid: bidDB) {
               if(bid.getProjectId() == projectId) {
                   projectBids.add(bid);
               }
           }

           Collections.sort(projectBids, Comparator.comparingDouble(Bids::getPrice));
           Bids winner = (projectBids.isEmpty()) ? null : projectBids.get(0);
           return new ResponseEntity<>(winner, HttpStatus.OK);
       } catch (Exception e) {
           logger.error("Error fetching data from DB");
           return null;
       }
    }
}
