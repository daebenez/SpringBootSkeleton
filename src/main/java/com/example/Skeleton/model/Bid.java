package com.example.Skeleton.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
public class Bid {

    @Id
    private long id;
    private long bidderId;
    private String name;
    private long projectId;
    private String bidDate;
    private double bidPrice;

    public Bid() {

    }

    public Bid(long id, long bidderId, String name, long projectId, String bidDate, double bidPrice) {
        this.id = id;
        this.bidderId = bidderId;
        this.name = name;
        this.projectId = projectId;
        this.bidDate = bidDate;
        this.bidPrice = bidPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBidderId() {
        return bidderId;
    }

    public void setBidderId(long bidderId) {
        this.bidderId = bidderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getBidDate() {
        return bidDate;
    }

    public void setBidDate(String bidDate) {
        this.bidDate = bidDate;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }
}
