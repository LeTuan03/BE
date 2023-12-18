package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    RoleEntity findByName(String name);
    List<RoleEntity> findByName(Optional<String> query);
}
