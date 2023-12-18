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
@Table(name = "donors")
public class DonorEntity {
    @Id
    @Column(name = "donor_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donorId;
    private String name;
    private String amount;
    private Timestamp donate_date;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private CampaignEntity campaignEntity;

}
