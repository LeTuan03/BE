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
@Table(name="milestiones")
public class MilestoneEntity {
    @Id
    @Column(name = "milestione_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int milestoneId;
    private String name;
    private String description;
    private Timestamp create_at;
    private Timestamp end_date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private CampaignEntity campaignEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_Status_id", referencedColumnName = "current_Status_id")
    private CurrentStatusEntity currentStatusEntity;

}