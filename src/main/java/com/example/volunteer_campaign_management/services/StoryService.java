package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.MilestoneDTO;
import com.example.volunteer_campaign_management.dtos.StoryDTO;
import com.example.volunteer_campaign_management.entities.StoryEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface StoryService {
    StoryEntity createNewStory(String name, String content, String title, MultipartFile image,String created_at, int campaginID);
    Boolean deleteStory(int storyId);
    List<StoryDTO> getAllStory();

    StoryDTO getStoryById(int storyId);

    List<StoryDTO> searchStory(Optional<String> query);

    ResponseEntity<Object> updateNew(int id, String name, String content, String title, MultipartFile image, Timestamp createdAt, int campaginID);

//    StoryDTO updateStory(int storyID, StoryDTO storyDTO);
}