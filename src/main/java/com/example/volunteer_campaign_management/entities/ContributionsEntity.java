package com.example.volunteer_campaign_management.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contributions")
public class ContributionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "donation_day")
    private Date donationDay;
    @Column(name = "price")
    private Float price;

    public ContributionsEntity() {
    }

    public ContributionsEntity(int id, String name, String description, Date donationDay, Float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.donationDay = donationDay;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDonationDay() {
        return donationDay;
    }

    public void setDonationDay(Date donationDay) {
        this.donationDay = donationDay;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
