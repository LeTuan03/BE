package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.ContributionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionsRepository extends JpaRepository<ContributionsEntity, Integer> {
    @Query("SELECT c FROM ContributionsEntity c ORDER BY c.id")
    Page<ContributionsEntity> findAllSortedById(Pageable pageable);
    @Query(value = "SELECT * from contributions order by donation_day desc", nativeQuery = true)
    Page<ContributionsEntity> findContributionsEntities(Pageable pageable);
    @Query("SELECT e FROM ContributionsEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<ContributionsEntity> findByNameContri(String name, Pageable pageable);
}
