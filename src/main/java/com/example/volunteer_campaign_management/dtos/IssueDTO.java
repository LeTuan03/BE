package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueDTO {
    private int issueId;
    private String title;
    private String description;
    private String assignee;
    private Timestamp start_date;
    private Timestamp due_date;
    private int userId;
    private int taskReportId;
    private int statusId;
    private String userName;
    private String taskReportName;
    private String currentStatusName;

}
