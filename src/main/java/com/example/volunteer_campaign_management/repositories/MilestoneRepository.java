package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.MilestoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MilestoneRepository extends JpaRepository<MilestoneEntity,Integer> {
    @Override
    List<MilestoneEntity> findAll();

    List<MilestoneEntity> findByCampaignEntity(CampaignEntity campaignEntity);
    List<MilestoneEntity> findByCurrentStatusEntity(CurrentStatusEntity currentStatusEntity);
    List<MilestoneEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(Optional<String> query, Optional<String> query1);

}