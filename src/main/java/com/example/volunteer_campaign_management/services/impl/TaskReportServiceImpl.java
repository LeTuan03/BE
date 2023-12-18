package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.TaskReportDTO;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.TaskReportEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.AccountRepository;
import com.example.volunteer_campaign_management.repositories.CampaignRepository;
import com.example.volunteer_campaign_management.repositories.CurrentStatusRepository;
import com.example.volunteer_campaign_management.repositories.TaskReportRepository;
import com.example.volunteer_campaign_management.services.TaskReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskReportServiceImpl implements TaskReportService {
    private final TaskReportRepository taskReportRepository;
    private final CampaignRepository campaignRepository;
    private final CurrentStatusRepository currentStatusRepository;
    private final AccountRepository accountRepository;
    private final MapperUtil mapperUtil;

    @Override
    public TaskReportDTO createNewTaskReport(TaskReportDTO taskReportDTO) {
        try {
            TaskReportEntity taskReportEntity = new TaskReportEntity();
            taskReportEntity.setTaskReport_Id(taskReportDTO.getTaskReportId());
            taskReportEntity.setName(taskReportDTO.getName());
            taskReportEntity.setDescription(taskReportDTO.getDescription());
            taskReportEntity.setTitle(taskReportDTO.getTitle());
            taskReportEntity.setDue_date(taskReportDTO.getDue_date());
            taskReportEntity.setNote(taskReportDTO.getNote());
            taskReportEntity.setAccountEntity(accountRepository.getOne(taskReportDTO.getUserId()));
            taskReportEntity.setCampaignEntity(campaignRepository.getOne(taskReportDTO.getCampaignId()));
            taskReportEntity.setCurrentStatusEntity(currentStatusRepository.getOne(taskReportDTO.getStatusId()));
            taskReportRepository.save(taskReportEntity);
            return taskReportDTO;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }


    @Override
    public TaskReportDTO getTaskReportById(int taskReportId) {
        try{
            TaskReportEntity taskReportEntity = taskReportRepository.findById(taskReportId).get();
            TaskReportDTO taskReportDTO = new TaskReportDTO();
            taskReportDTO.setName(taskReportEntity.getName());
            taskReportDTO.setTitle(taskReportEntity.getTitle());
            taskReportDTO.setDescription(taskReportEntity.getDescription());
            taskReportDTO.setDue_date(taskReportEntity.getDue_date());
            taskReportDTO.setNote(taskReportEntity.getNote());
            taskReportDTO.setUserId(taskReportEntity.getAccountEntity().getAccountId());
            taskReportDTO.setCampaignId(taskReportEntity.getCampaignEntity().getCampaignId());
            taskReportDTO.setStatusId(taskReportEntity.getCurrentStatusEntity().getStatusId());
            taskReportDTO.setUserName(taskReportEntity.getAccountEntity().getProfileEntity().getLastname() + " " + taskReportEntity.getAccountEntity().getProfileEntity().getFirstname());
            taskReportDTO.setCampaignName(taskReportEntity.getCampaignEntity().getName());
            taskReportDTO.setStatusName(taskReportEntity.getCurrentStatusEntity().getName());
            taskReportDTO.setTaskReportId(taskReportId);
            return taskReportDTO;
        } catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<TaskReportDTO> searchTaskReport(Optional<String> query) {
        try {
            List<TaskReportEntity> taskReportEntities = new ArrayList<>();
            if (!query.isPresent()) {
                return  getAllTaskReport();
            }
            taskReportEntities = taskReportRepository.findByNameContainsIgnoreCaseOrDescriptionContainingIgnoreCaseOrTitleContainingIgnoreCaseOrNoteContainsIgnoreCase(query,query,query,query);
            List<CampaignEntity> campaignEntities = this.campaignRepository.findByNameContainsIgnoreCase(query);
            for (CampaignEntity campaignEntity : campaignEntities) {
                taskReportEntities.addAll(this.taskReportRepository.findByCampaignEntity(campaignEntity));
            }
            List<CurrentStatusEntity> currentStatusEntities = this.currentStatusRepository.findByNameContainingIgnoreCase(query);
            for (CurrentStatusEntity currentStatusEntity : currentStatusEntities) {
                taskReportEntities.addAll(this.taskReportRepository.findByCurrentStatusEntity(currentStatusEntity));
            }

            return mapperUtil.mapToListTaskReportDTO(taskReportEntities);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;

    }

    @Override
    public TaskReportDTO updateTaskReport(int taskReportId, TaskReportDTO taskReportDTO) {
        try {
            TaskReportEntity taskReportEntity = taskReportRepository.getOne(taskReportId);
            taskReportEntity.setName(taskReportDTO.getName());
            taskReportEntity.setDescription(taskReportDTO.getDescription());
            taskReportEntity.setTitle(taskReportDTO.getTitle());
            taskReportEntity.setDue_date(taskReportDTO.getDue_date());
            taskReportEntity.setNote(taskReportDTO.getNote());
            taskReportEntity.setAccountEntity(accountRepository.getOne(taskReportDTO.getUserId()));
            taskReportEntity.setCampaignEntity(campaignRepository.getOne(taskReportDTO.getCampaignId()));
            taskReportEntity.setCurrentStatusEntity(currentStatusRepository.getOne(taskReportDTO.getStatusId()));
            taskReportRepository.save(taskReportEntity);
            return taskReportDTO;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<TaskReportDTO> getAllTaskReport() {
        try {
            List<TaskReportEntity> taskReportEntities = taskReportRepository.findAll();
            List<TaskReportDTO> taskReportDTOS = mapperUtil.mapToListTaskReportDTO(taskReportEntities);
            return taskReportDTOS;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
