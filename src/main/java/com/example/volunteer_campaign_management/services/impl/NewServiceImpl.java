package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.NewDTO;
import com.example.volunteer_campaign_management.dtos.ResponseNewDTO;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.MilestoneEntity;
import com.example.volunteer_campaign_management.entities.NewEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.NewRepository;
import com.example.volunteer_campaign_management.services.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewServiceImpl implements NewService {

    private final NewRepository newRepository;
    private final MapperUtil mapperUtil;
    private  CloudinaryService cloudinaryService;
    @Autowired
    public NewServiceImpl(NewRepository newRepository, MapperUtil mapperUtil, CloudinaryService cloudinaryService) {
        this.newRepository = newRepository;
        this.mapperUtil = mapperUtil;
        this.cloudinaryService = cloudinaryService;
    }




    @Override
    public ResponseNewDTO createNew(String title, String content, MultipartFile image) {
        try {
            NewEntity newEntity = new NewEntity();
            newEntity.setContent(content);
            newEntity.setTitle(title);
            long currentTimeMillis = System.currentTimeMillis();

            // Tạo đối tượng Timestamp từ thời gian hiện tại
            Timestamp currentTimestamp = new Timestamp(currentTimeMillis);
            newEntity.setCreated_at(currentTimestamp);
            newEntity.setImage(cloudinaryService.uploadImage(image));
            newRepository.save(newEntity);

            ResponseNewDTO responseNewDTO = new ResponseNewDTO();
            responseNewDTO.setImage(newEntity.getImage());
            responseNewDTO.setCreated_at(newEntity.getCreated_at());
            responseNewDTO.setNewId(newEntity.getNewId());
            responseNewDTO.setTitle(newEntity.getTitle());
            responseNewDTO.setContent(newEntity.getContent());
            return responseNewDTO;

        } catch(IOException e) {
            e.getMessage();
        }
        return null;
    }



    @Override
    public boolean deleteNew(int newId) {
        try {
            newRepository.deleteById(newId);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public List<ResponseNewDTO> getAllNews() {
        List<NewEntity> newEntities = newRepository.findAll();
        return newEntities.stream()
                .map(entity -> {
                    ResponseNewDTO newDTO = new ResponseNewDTO();
                    newDTO.setNewId(entity.getNewId());
                    newDTO.setTitle(entity.getTitle());
                    newDTO.setContent(entity.getContent());
                    newDTO.setCreated_at(entity.getCreated_at());
                    newDTO.setImage(entity.getImage());
                    return newDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseNewDTO getNewById(int newId) {
        Optional<NewEntity> optionalNewEntity = newRepository.findById(newId);
        if (optionalNewEntity.isPresent()) {
            NewEntity newEntity = optionalNewEntity.get();
            ResponseNewDTO newDTO = new ResponseNewDTO();
            newDTO.setNewId(newEntity.getNewId());
            newDTO.setTitle(newEntity.getTitle());
            newDTO.setContent(newEntity.getContent());
            newDTO.setCreated_at(newEntity.getCreated_at());
            newDTO.setImage(newEntity.getImage());

            return newDTO;
        } else {
            // Handle the case where the NewEntity with the specified ID is not found
            return null;
        }
    }

    @Override
    public List<ResponseNewDTO> searchNews(Optional<String> keyword) {
        if (!keyword.isPresent()) {
            return getAllNews();
        }
        List<NewEntity> searchedNews = newRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        return searchedNews.stream()
                .map(entity -> {
                    ResponseNewDTO newDTO = new ResponseNewDTO();
                    newDTO.setNewId(entity.getNewId());
                    newDTO.setTitle(entity.getTitle());
                    newDTO.setContent(entity.getContent());
                    newDTO.setCreated_at(entity.getCreated_at());
                    return newDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> updateNew(int newId,String title, String content, MultipartFile image) {
        try {
            NewEntity newEntity = newRepository.findById(newId).get();
            newEntity.setTitle(title);
            newEntity.setContent(content);
            newEntity.setImage(cloudinaryService.uploadImage(image));
            newRepository.save(newEntity);
        return new ResponseEntity<>(newEntity, HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
