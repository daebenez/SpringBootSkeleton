package com.example.Skeleton.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bid {

    @Id
    private long id;

    public Bid() {

    }

    public Bid(long id, long bidderId, long projectId, String name, String bidDate,  double bidPrice) {
        this.id = id;
        this.bidderId = bidderId;
        this.projectId = projectId;
        this.name = name;
        this.bidDate = bidDate;
        this.bidPrice = bidPrice;
    }

    private long bidderId;

    private long projectId;

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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private String name;

    private String bidDate;

    private double bidPrice;



}
