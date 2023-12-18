package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.TaskReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TaskReportRepository extends JpaRepository<TaskReportEntity, Integer> {
    List<TaskReportEntity> findByNameContainsIgnoreCase(Optional<String> query);
    List<TaskReportEntity> findByNameContainsIgnoreCaseOrDescriptionContainingIgnoreCaseOrTitleContainingIgnoreCaseOrNoteContainsIgnoreCase(
            Optional<String> query,Optional<String> query1,Optional<String> query2,Optional<String> query3
    );

    Collection<? extends TaskReportEntity> findByCampaignEntity(CampaignEntity campaignEntity);

    Collection<? extends TaskReportEntity> findByCurrentStatusEntity(CurrentStatusEntity currentStatusEntity);
}
