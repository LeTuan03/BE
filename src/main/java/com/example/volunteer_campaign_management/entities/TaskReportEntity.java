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
@Table(name = "taskreport")
public class TaskReportEntity {
    @Id
    @Column(name = "taskreport_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskReport_Id;
    private String name;
    private String title;
    private String description;
    private Timestamp due_date;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private CampaignEntity campaignEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_Status_id", referencedColumnName = "current_Status_id")
    private CurrentStatusEntity currentStatusEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private AccountEntity accountEntity;
}
