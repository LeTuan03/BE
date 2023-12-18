package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.ResponseNewDTO;
import com.example.volunteer_campaign_management.services.NewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class NewController {

    @Autowired
    private NewService newService;

    @PostMapping("/news/create")
    public ResponseNewDTO createNew(@RequestParam("image")MultipartFile image,
                                    @RequestParam("title") String title,
                                    @RequestParam("content") String content) {
        return newService.createNew(title, content, image);
    }

    @PutMapping("/news/update/{newId}")
    public ResponseEntity<Object> updateNew(@PathVariable(value = "newId") int newId,
                                            @RequestParam("image")MultipartFile image,
                                            @RequestParam("title") String title,
                                            @RequestParam("content") String content) {
        return newService.updateNew(newId,title, content, image);
    }


    @DeleteMapping("/news/delete/{newId}")
    public boolean deleteNew(@PathVariable int newId) {
        return newService.deleteNew(newId);
    }

    @GetMapping("/news/list")
    public List<ResponseNewDTO> getAllNews() {
        return newService.getAllNews();
    }

    @GetMapping("/news/getById/{newId}")
    public ResponseNewDTO getNewById(@PathVariable int newId) {
        return newService.getNewById(newId);
    }
    @GetMapping (value = {"/news/search","/news/search/{query}"})
    public List<ResponseNewDTO> searchNews(@PathVariable(value = "query") Optional<String> query){
        return  newService.searchNews(query);
    }
}