package com.example.volunteer_campaign_management.controller;
import com.example.volunteer_campaign_management.dtos.ContributionsDTO;
import com.example.volunteer_campaign_management.entities.ContributionsEntity;
import com.example.volunteer_campaign_management.services.ContributionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
public class ContributionController {
    @Autowired
    private ContributionsService contributionService;
    @GetMapping("/getAllContributions")
    public ResponseEntity<Map<String, Object>> getAllContributions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ContributionsEntity> contributionsPage = contributionService.findAll(pageable);

            List<ContributionsEntity> content = contributionsPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("contributions", content);
            response.put("totalPages", contributionsPage.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions appropriately
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchContributionsByName(
            @RequestParam("name") String name,
            Pageable pageable
    ) {
        Page<ContributionsEntity> contributionsPage = contributionService.findContributionsByName(name, pageable);
        List<ContributionsEntity> content = contributionsPage.getContent();

        // Chuyển đổi Entities thành DTOs
        List<ContributionsDTO> contributionsDTOs = content.stream()
                .map(contribution -> new ContributionsDTO(
                        contribution.getId(),
                        contribution.getName(),
                        contribution.getDescription(),
                        contribution.getDonationDay(),
                        contribution.getPrice()
                ))
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("contributions", contributionsDTOs);
        response.put("totalPages", contributionsPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
