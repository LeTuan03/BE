package com.example.volunteer_campaign_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campaign")
public class CampaignEntity {
    @Id
    @Column(name = "campaign_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaignId;
    private String name;
    private Timestamp start_date;
    private Timestamp end_date;
    private String description;
    private String title;
    private String location;
    private boolean status;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_Status_id", referencedColumnName = "current_Status_id")
    private CurrentStatusEntity statusIssueEntity;

}
