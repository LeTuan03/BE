package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.GeneralReportDTO;
import com.example.volunteer_campaign_management.dtos.IssueDTO;
import com.example.volunteer_campaign_management.services.GeneralReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class GeneralReportController {
    private final GeneralReportService generalReportService;

    @PostMapping("/createGeneralReport")
    public GeneralReportDTO createNewGeneralReport(@RequestBody GeneralReportDTO generalReportDTO) {
        return generalReportService.createNewGeneralReport(generalReportDTO);
    }

    @PutMapping("/updateGeneralReport/{id}")
    public GeneralReportDTO updateGeneralReport(@PathVariable(value = "id") int generalReportId, @RequestBody GeneralReportDTO generalReportDTO) {
        return generalReportService.updateGeneralReport(generalReportId, generalReportDTO);
    }

    @GetMapping("/generalReport/{id}")
    public GeneralReportDTO getGeneralReportById(@PathVariable(value = "id") int generalReportId) {
        return generalReportService.getGeneralReportById(generalReportId);
    }

    @GetMapping("/generalReports")
    public java.util.List<GeneralReportDTO> getAllGeneralReport() {
        return generalReportService.getAllGeneralReport();
    }


    @GetMapping (value = {"/searchGeneralReports","/searchGeneralReports/{query}"})
    public java.util.List<GeneralReportDTO> searchGeneralReports(@PathVariable(value = "query") Optional<String> query){
        return generalReportService.searchGeneralReport(query);
    }

    @DeleteMapping("/deleteGeneralReport/{id}")
    public boolean deleteGeneralReport(@PathVariable(value = "id") int generalReportId) {
        return generalReportService.deleteGeneralReport(generalReportId);
    }
}
