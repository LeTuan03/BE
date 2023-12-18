package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StoryDTO {
    private int storyId;
    private String name;
    private String content;
    private String title;
    private Timestamp created_at;
    private String image;
    private int campaignId;
    private String campaignName;


}
