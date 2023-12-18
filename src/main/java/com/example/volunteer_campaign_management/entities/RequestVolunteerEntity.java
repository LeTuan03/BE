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
@Table (name="requestvolunteer")
public class RequestVolunteerEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestVolunteerId;
    private String name;
    private String email;
    private String phone;
    private Timestamp date_of_birth;
    private String address;
    @Column(name = "reason")
    private String reason;
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private CampaignEntity campaignEntity;

}
