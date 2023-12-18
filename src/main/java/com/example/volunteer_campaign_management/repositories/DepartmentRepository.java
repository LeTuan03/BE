package com.example.volunteer_campaign_management.repositories;


import com.example.volunteer_campaign_management.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {

    List<DepartmentEntity> findByNameContainingIgnoreCase(Optional<String> query);
}
