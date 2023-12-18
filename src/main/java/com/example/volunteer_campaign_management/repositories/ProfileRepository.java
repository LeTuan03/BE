package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {
 List<ProfileEntity> findByAddressContainingIgnoreCaseOrFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(Optional<String> query,Optional<String> query1,Optional<String> query2);
 ProfileEntity findById(int profileID);
}
