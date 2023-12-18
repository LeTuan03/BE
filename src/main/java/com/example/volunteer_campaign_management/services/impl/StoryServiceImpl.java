package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.MilestoneDTO;
import com.example.volunteer_campaign_management.dtos.StoryDTO;
import com.example.volunteer_campaign_management.entities.*;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.CampaignRepository;
import com.example.volunteer_campaign_management.repositories.StoryRepository;
import com.example.volunteer_campaign_management.services.StoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoryServiceImpl implements StoryService {
    private final StoryRepository storyRepository;
    private final CampaignRepository campaignRepository;
    private final MapperUtil mapperUtil;
    private final CloudinaryService cloudinaryService;

    @Override
    public StoryEntity createNewStory(String name, String content, String title, MultipartFile image,String created_at, int campaginID) {
        try {
            CampaignEntity select = campaignRepository.findByIdCom(campaginID);
            StoryEntity storyEntity = new StoryEntity();
            storyEntity.setName(name);
            storyEntity.setContent(content);
            storyEntity.setTitle(title);
            storyEntity.setCreated_at(new Timestamp(System.currentTimeMillis()));
            storyEntity.setImage(cloudinaryService.uploadImage(image));
            storyEntity.setCampaignEntity(select);
            storyRepository.save(storyEntity);
            return storyEntity;
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public Boolean deleteStory(int storyId) {
        try{
            if(storyRepository.getOne(storyId) != null){
                storyRepository.deleteById(storyId);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }
    @Override
    public StoryDTO getStoryById(int storyId) {
        try{
            StoryEntity storyEntity = storyRepository.findById(storyId).get();
            StoryDTO storyDTO = new StoryDTO();
            storyDTO.setName(storyEntity.getName());
            storyDTO.setContent(storyEntity.getContent());
            storyDTO.setTitle(storyEntity.getTitle());
            storyDTO.setCreated_at(storyEntity.getCreated_at());
            storyDTO.setImage(storyEntity.getImage());
            storyDTO.setCampaignId(storyEntity.getCampaignEntity().getCampaignId());
            storyDTO.setCampaignName(storyEntity.getCampaignEntity().getName());
            storyDTO.setStoryId(storyId);
            return storyDTO;
        } catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<StoryDTO> searchStory(Optional<String> query) {
        try {
            List<StoryEntity> storyEntities = new ArrayList<>();
            if (!query.isPresent()) {
                return  getAllStory();
            }
            storyEntities = storyRepository.findByNameContainsIgnoreCaseOrContentContainingIgnoreCaseOrTitleContainingIgnoreCase(query,query,query);
            List<CampaignEntity> campaignEntities = this.campaignRepository.findByNameContainsIgnoreCase(query);
            for (CampaignEntity campaignEntity : campaignEntities) {
                storyEntities.addAll(this.storyRepository.findByCampaignEntity(campaignEntity));
            }

            return mapperUtil.mapToListStory(storyEntities);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;

    }

    @Override
    public ResponseEntity<Object> updateNew(int id, String name, String content, String title, MultipartFile image, Timestamp createdAt, int campaginID) {
        try {
            StoryEntity storyEntity = storyRepository.getOne(id);
            storyEntity.setStoryId(id);
            storyEntity.setName(name);
            storyEntity.setContent(content);
            storyEntity.setTitle(title);
            storyEntity.setImage(cloudinaryService.uploadImage(image));
            storyEntity.setCreated_at(createdAt);
            storyEntity.setCampaignEntity(campaignRepository.getOne(campaginID));
            storyRepository.save(storyEntity);
            return new ResponseEntity<>(storyEntity, HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(ex, HttpStatus.OK);
        }

    }

//    @Override
//    public StoryDTO updateStory(int storyID, StoryDTO storyDTO) {
//      try{
//          StoryEntity story = storyRepository.findByIdCom(storyID);
//          if(story == null ){
//              throw new RuntimeException("Story not found with ID: " + storyID);
//          }
//        story.setName(storyDTO.getName());
//        story.setContent(storyDTO.getContent());
//        story.setTitle(storyDTO.getTitle());
//        story.setCreated_at(storyDTO.getCreated_at());
//        story.setImage(story.getImage());
//        CampaignEntity campaignEntity = campaignRepository.getOne(storyDTO.getCampaignId());
//        story.setCampaignEntity(campaignEntity);
//        storyRepository.save(story);
//        return storyDTO;
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//        return null;
//    }

    @Override
    public List<StoryDTO> getAllStory() {
        try {
            List<StoryEntity> storyEntities = storyRepository.findAll();
            List<StoryDTO> storyDTOS = mapperUtil.mapToListStoryDTO(storyEntities);
            return storyDTOS;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}