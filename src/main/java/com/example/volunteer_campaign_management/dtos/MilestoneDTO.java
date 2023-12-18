package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MilestoneDTO {
    private int milestione_id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Timestamp create_at;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Timestamp end_date;
    private int campaign_id;
    private int current_Status_id;
    private String campain_name;
    private String current_status_name;
}
