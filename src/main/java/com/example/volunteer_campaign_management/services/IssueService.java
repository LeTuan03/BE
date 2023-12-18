package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.CampaignDTO;
import com.example.volunteer_campaign_management.dtos.IssueDTO;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    IssueDTO createNewIssue(IssueDTO issueDTO);
    Boolean deleteIssue(int issueId);
    List<IssueDTO> getAllIssue();

    IssueDTO getIssueById(int issueId);

    List<IssueDTO> searchIssue(Optional<String> query);
    IssueDTO updateIssue(int issueId, IssueDTO issueDTO);
}
