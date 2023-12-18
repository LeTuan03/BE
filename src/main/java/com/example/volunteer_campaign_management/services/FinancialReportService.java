package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.FinancialReportDTO;

import java.util.List;
import java.util.Optional;

public interface FinancialReportService {
    List<FinancialReportDTO> getAllFinancialReport();
    FinancialReportDTO createFinancialReport(FinancialReportDTO financialReportDTO);

    FinancialReportDTO updateFinancialReport(int financialReportID, FinancialReportDTO financialReportDTO);

    boolean deleteFinancialReport(int financialReportID);

    List<FinancialReportDTO> searchFinancialReport(Optional<String> query);

    FinancialReportDTO getFinancialReportById(int financialReportID);
}