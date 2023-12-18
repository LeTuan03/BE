package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.CampaignDTO;
import com.example.volunteer_campaign_management.dtos.IssueDTO;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.IssueEntity;
import com.example.volunteer_campaign_management.entities.*;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.AccountRepository;
import com.example.volunteer_campaign_management.repositories.CurrentStatusRepository;
import com.example.volunteer_campaign_management.repositories.IssueRepository;
import com.example.volunteer_campaign_management.repositories.TaskReportRepository;
import com.example.volunteer_campaign_management.services.IssueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final TaskReportRepository taskReportRepository;
    private final MapperUtil mapperUtil;
    private final AccountRepository accountRepository;
    private final CurrentStatusRepository currentStatusRepository;


    @Override
    public IssueDTO createNewIssue(IssueDTO issueDTO) {
        try {
            IssueEntity issueEntity = new IssueEntity();
            issueEntity.setIssueId(issueDTO.getIssueId());
            issueEntity.setTitle(issueDTO.getTitle());
            issueEntity.setDescription(issueDTO.getDescription());
            issueEntity.setAssignee(issueDTO.getAssignee());
            issueEntity.setStart_date(issueDTO.getStart_date());
            issueEntity.setDue_date(issueDTO.getDue_date());
            issueEntity.setAccountEntity(accountRepository.getOne(issueDTO.getUserId()));
            issueEntity.setTaskReportEntity(taskReportRepository.getOne(issueDTO.getTaskReportId()));
            issueEntity.setCurrentStatusEntity(currentStatusRepository.getOne(issueDTO.getStatusId()));
            issueRepository.save(issueEntity);
            return issueDTO;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public Boolean deleteIssue(int issueId) {
        try {
            if (issueRepository.getOne(issueId) != null) {
                issueRepository.deleteById(issueId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
    @Override
    public IssueDTO getIssueById(int issueId) {
        try{
            IssueEntity issueEntity = issueRepository.findById(issueId).get();
            IssueDTO issueDTO = new IssueDTO();
            issueDTO.setTitle(issueEntity.getTitle());
            issueDTO.setDescription(issueEntity.getDescription());
            issueDTO.setAssignee(issueEntity.getAssignee());
            issueDTO.setStart_date(issueEntity.getStart_date());
            issueDTO.setDue_date(issueEntity.getDue_date());
            issueDTO.setUserId(issueEntity.getAccountEntity().getAccountId());
            issueDTO.setTaskReportId(issueEntity.getTaskReportEntity().getTaskReport_Id());
            issueDTO.setStatusId(issueEntity.getCurrentStatusEntity().getStatusId());
            issueDTO.setUserName(issueEntity.getAccountEntity().getProfileEntity().getFirstname());
            issueDTO.setTaskReportName(issueEntity.getTaskReportEntity().getName());
            issueDTO.setCurrentStatusName(issueEntity.getCurrentStatusEntity().getName());
            issueDTO.setIssueId(issueId);
            return issueDTO;
        } catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<IssueDTO> getAllIssue() {
        try{
            List<IssueEntity> issueEntities = issueRepository.findAll();
            List<IssueDTO> issueDTOS = mapperUtil.maptoListIsssueDTO(issueEntities);
            return issueDTOS;
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<IssueDTO> searchIssue(Optional<String> query) {
        List<IssueEntity> issueDTOS = new ArrayList<>();
        try{
            if (!query.isPresent()) {
                return getAllIssue();
            }
            else {
                issueDTOS = issueRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssigneeContainingIgnoreCase(query,query, query);

            }
        } catch (Exception e) {
            e.getMessage();
        }
        return mapperUtil.maptoListIsssueDTO(issueDTOS);
    }

    @Override
    public IssueDTO updateIssue(int issueId, IssueDTO issueDTO) {
        try {
            IssueEntity issueEntity = issueRepository.getOne(issueId);
            issueEntity.setTitle(issueDTO.getTitle());
            issueEntity.setDescription(issueDTO.getDescription());
            issueEntity.setAssignee(issueDTO.getAssignee());
            issueEntity.setStart_date(issueDTO.getStart_date());
            issueEntity.setDue_date(issueDTO.getDue_date());
            issueEntity.setAccountEntity(accountRepository.getOne(issueDTO.getUserId()));
            issueEntity.setTaskReportEntity(taskReportRepository.getOne(issueDTO.getTaskReportId()));
            issueEntity.setCurrentStatusEntity(currentStatusRepository.getOne(issueDTO.getStatusId()));
            issueRepository.save(issueEntity);
            return issueDTO;
        } catch (Exception e) {
            e.printStackTrace(); // Thay vì e.getMessage(), in chi tiết lỗi
            return null;
        }
    }
}
