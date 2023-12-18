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
@Table(name = "generalreport")
public class GeneralReportEntity {
    @Id
    @Column(name = "generalreport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int generalReportId;
    private String attachment;
    private Timestamp created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private CampaignEntity campaignEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_Status_id", referencedColumnName = "current_Status_id")
    private CurrentStatusEntity currentStatusEntity;
}
