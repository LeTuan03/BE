package com.example.volunteer_campaign_management.entities;

import javax.persistence.*;

@Entity
@Table(name = "media")
public class MediaCampaignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private int mediaId;
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaignEntity;
    @Column(name = "image")
    private String image;
    @Column(name = "video")
    private String video;

    public MediaCampaignEntity() {
    }

    public MediaCampaignEntity(int mediaId, CampaignEntity campaignEntity, String image, String video) {
        this.mediaId = mediaId;
        this.campaignEntity = campaignEntity;
        this.image = image;
        this.video = video;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public CampaignEntity getCampaignEntity() {
        return campaignEntity;
    }

    public void setCampaignEntity(CampaignEntity campaignEntity) {
        this.campaignEntity = campaignEntity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}