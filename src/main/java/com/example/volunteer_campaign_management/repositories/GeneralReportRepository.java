package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.GeneralReportEntity;
import com.example.volunteer_campaign_management.entities.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralReportRepository extends JpaRepository<GeneralReportEntity, Integer> {
    List<GeneralReportEntity> findByAttachmentContainingIgnoreCase(Optional<String> query1);

    Collection<? extends GeneralReportEntity> findByCampaignEntity(CampaignEntity campaignEntity);

    Collection<? extends GeneralReportEntity> findByCurrentStatusEntity(CurrentStatusEntity currentStatusEntity);
}
