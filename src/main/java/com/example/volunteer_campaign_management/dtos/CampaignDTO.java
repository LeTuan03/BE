package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CampaignDTO {
    private int campaignId;
    private String name;
    private Timestamp start_date;
    private Timestamp end_date;
    private String description;
    private String title;
    private String location;
    private int statusId;
    private String statusName;
    private Boolean status;
    private String image;
}
