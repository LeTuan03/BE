package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.MilestoneEntity;
import com.example.volunteer_campaign_management.entities.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewRepository extends JpaRepository<NewEntity, Integer> {
    // Add custom queries if needed
    List<NewEntity> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(Optional<String> query, Optional<String> query1);

}
