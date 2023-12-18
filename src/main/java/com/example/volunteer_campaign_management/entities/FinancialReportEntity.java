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
@Table(name="financialreport")
public class FinancialReportEntity {
    @Id
    @Column(name = "financialreport_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int financialReportId;
    private String name;
    private String amount;
    private String total_expenses;
    private String description;
    @Column(name = "created_at")
    private Timestamp createdat;
    private String note;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private CampaignEntity campaignEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private AccountEntity accountEntity;
}