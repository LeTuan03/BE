package com.example.volunteer_campaign_management.dtos;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ContributionsDTO {
    private int id;
    private String name;
    private String description;
    private String donationDay;
    private Float price;

    public ContributionsDTO(int id, String name, String description, Date donationDay, Float price) {
        this.id = id;
        this.name = name;
        this.description = description;

        // Chuyển đổi timestamp sang định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.donationDay = dateFormat.format(donationDay);

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

    public String getDonationDayFormatted() {
        return donationDay;
    }

    public void setDonationDayFormatted(String donationDayFormatted) {
        this.donationDay = donationDayFormatted;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    // Getters and setters
}
