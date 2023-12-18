package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Integer> {

    List<StoryEntity> findByNameContainsIgnoreCaseOrContentContainingIgnoreCaseOrTitleContainingIgnoreCase(
            Optional<String> query,Optional<String> query1,Optional<String> query2
    );

    Collection<? extends StoryEntity> findByCampaignEntity(CampaignEntity campaignEntity);
    @Query(value ="select  * from story where story_id = ?1", nativeQuery = true)
    StoryEntity findByIdCom(int id);
}
