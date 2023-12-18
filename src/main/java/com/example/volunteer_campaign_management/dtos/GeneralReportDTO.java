package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeneralReportDTO {
    private int generalReportId;
    private String attachment;
    private Timestamp created_at;
    private int campaignId;
    private int statusId;
    private String campaignName;
    private String statusName;
}
