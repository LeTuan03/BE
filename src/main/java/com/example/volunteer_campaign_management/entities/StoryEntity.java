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
@Table(name = "story")
public class StoryEntity {
    @Id
    @Column(name = "story_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storyId;
    private String name;
    private String content;
    private String title;
    private Timestamp created_at;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")
    private CampaignEntity campaignEntity;
}
