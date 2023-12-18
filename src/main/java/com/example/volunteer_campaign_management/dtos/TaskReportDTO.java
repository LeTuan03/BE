package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskReportDTO {
    private int taskReportId;
    private String name;
    private String description;
    private String title;
    private Timestamp due_date;
    private String note;
    private int userId;
    private int campaignId;
    private int statusId;
    private String userName;
    private String campaignName;
    private String statusName;

}
