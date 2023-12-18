package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.MilestoneDTO;
import com.example.volunteer_campaign_management.dtos.NewDTO;
import com.example.volunteer_campaign_management.dtos.ResponseNewDTO;
import com.example.volunteer_campaign_management.entities.NewEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface NewService {
    ResponseNewDTO createNew(String title, String content, MultipartFile image);

    boolean deleteNew(int newId);
    List<ResponseNewDTO> getAllNews();
    ResponseNewDTO getNewById(int newId);
    List<ResponseNewDTO> searchNews(Optional<String> query);

    ResponseEntity<Object> updateNew(int newId,String title, String content, MultipartFile image);
}
