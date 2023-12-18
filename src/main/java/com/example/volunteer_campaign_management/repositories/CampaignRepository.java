package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.MilestoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Integer> {
    @Query(value = "SELECT * FROM volunteer_campaign_management.campaign", nativeQuery = true)
    List<CampaignEntity> getAllCampaigns();
    List<CampaignEntity> findByNameContainsIgnoreCase(Optional<String> query);
    List<CampaignEntity> findByStatusIssueEntity(CurrentStatusEntity currentStatusEntity);
    List<CampaignEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrLocationContainingIgnoreCaseOrTitleContainsIgnoreCase(Optional<String> query1, Optional<String> query2, Optional<String> query3, Optional<String> query4);
    @Query(value ="select  * from campaign where campaign_id = ?1", nativeQuery = true)
    CampaignEntity findByIdCom(int idCampagin);
}
