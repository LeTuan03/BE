package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.TaskReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrentStatusRepository extends JpaRepository<CurrentStatusEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM volunteer_campaign_management.current_status")
    List<CurrentStatusEntity> findCurrentStatusEntities();

    CurrentStatusEntity findByName(String query);

    List<CurrentStatusEntity> findByNameContainingIgnoreCase(Optional<String> query);
    @Query(value = "select * from current_status where current_Status_id = ?1", nativeQuery = true)
    CurrentStatusEntity findByIdCurrent(int idCurrentStatus);

}

