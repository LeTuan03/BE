package com.example.volunteer_campaign_management.repositories;


import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.DonorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<DonorEntity,Integer> {
    List<DonorEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(Optional<String> query, Optional<String> query1);

    List<DonorEntity> findByCampaignEntity(CampaignEntity campaignEntity);
    @Query("SELECT e FROM DonorEntity e ORDER BY e.donate_date DESC")
    List<DonorEntity> findAllSort();
    @Query("SELECT e FROM DonorEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<DonorEntity> searchByName(String name);
}
