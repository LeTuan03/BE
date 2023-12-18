package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.AccountDTO;
import com.example.volunteer_campaign_management.dtos.ProfileDTO;
import com.example.volunteer_campaign_management.entities.ProfileEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileEntity profileById(int accountId);
    ProfileDTO getProfileById(int profileId);

    ResponseEntity<Object> updateProfile(int accountId, String firstName, String lastName, String email, String phone, String address, MultipartFile avatar);

    // ProfileDTO updateProfile(int accountId, String firstName, String lastName, String email, String phone, String address, MultipartFile avatar);
}
