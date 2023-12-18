package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.FinancialReportDTO;
import com.example.volunteer_campaign_management.services.FinancialReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class FinacialReportController {
    @Autowired
    private final FinancialReportService financialReportService;
    @GetMapping("/financialReport/list")
    public List<FinancialReportDTO> getAllFinancialReport() {
        return financialReportService.getAllFinancialReport();
    }

    @PostMapping("/financialReport/create")
    public FinancialReportDTO createNewFinancialReport(@RequestBody @Valid FinancialReportDTO financialReportDTO){
        return financialReportService.createFinancialReport(financialReportDTO);
    }
    @GetMapping("/financialReport/{id}")
    public FinancialReportDTO getFinancialReportById(@PathVariable(value = "id") int financialReportID) {
        return financialReportService.getFinancialReportById(financialReportID);
    }
    @PutMapping("/financialReport/update/{id}")
    public FinancialReportDTO updateFinancialReport(@PathVariable(value = "id") @Valid int financialReportID, @RequestBody FinancialReportDTO financialReportDTO) {
        return financialReportService.updateFinancialReport(financialReportID,financialReportDTO);
    }
    @DeleteMapping("/financialReport/delete/{id}")
    public boolean deleteFinancialReport(@PathVariable(value = "id") int financialReportID) {
        return financialReportService.deleteFinancialReport(financialReportID);
    }
    @GetMapping (value = {"financialReport/search","financialReport/search/{query}"})
    public List<FinancialReportDTO> searchFinancialReport(@PathVariable(value = "query") Optional<String> query){
        return financialReportService.searchFinancialReport(query);
    }
}