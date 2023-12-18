package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.GeneralReportDTO;

import java.util.List;
import java.util.Optional;

public interface GeneralReportService {
    GeneralReportDTO createNewGeneralReport(GeneralReportDTO generalReportDTO);
    List<GeneralReportDTO> getAllGeneralReport();
    GeneralReportDTO getGeneralReportById(int generalReportId);
    List<GeneralReportDTO> searchGeneralReport(Optional<String> query);
    GeneralReportDTO updateGeneralReport(int generalReportId, GeneralReportDTO generalReportDTO);
    boolean deleteGeneralReport(int generalReportId);
}
