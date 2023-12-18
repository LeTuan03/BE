package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.CampaignDTO;
import com.example.volunteer_campaign_management.dtos.IssueDTO;
import com.example.volunteer_campaign_management.services.IssueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @GetMapping("/issues")
    public List<IssueDTO> getAllIssues() {
        return issueService.getAllIssue();
    }

    @PutMapping("/updateIssue/{id}")
    public IssueDTO updateIssue(@PathVariable(value = "id") int issueId, @RequestBody IssueDTO issueDTO) {
        return issueService.updateIssue(issueId, issueDTO);
    }

    @GetMapping("/issue/{id}")
    public IssueDTO getIssueById(@PathVariable(value = "id") int issueId){
        return issueService.getIssueById(issueId);
    }

    @PostMapping("/createIssue")
    public IssueDTO createNewIssue(@RequestBody IssueDTO issueDTO) {
        return issueService.createNewIssue(issueDTO);
    }

    @DeleteMapping("/deleteIssue/{id}")
    public boolean deleteIssue(@PathVariable(value = "id") int issueId) {
        return issueService.deleteIssue(issueId);
    }

    @GetMapping (value = {"/searchIssue","/searchIssue/{query}"})
    public java.util.List<IssueDTO> searchIssue(@PathVariable(value = "query") Optional<String> query){
        return issueService.searchIssue(query);
    }
}
