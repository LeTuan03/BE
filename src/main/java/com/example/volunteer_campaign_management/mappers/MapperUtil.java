package com.example.volunteer_campaign_management.mappers;

import com.example.volunteer_campaign_management.dtos.*;
import com.example.volunteer_campaign_management.entities.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MapperUtil {
    public List<FinancialReportDTO> mapToListFinancialReportDTO(List<FinancialReportEntity> financialReportEntities) {
        List<FinancialReportDTO> financialReportDTOS = new ArrayList<>();
        financialReportEntities.stream().forEach(financialReportEntity -> {
            FinancialReportDTO financialReportDTO = new FinancialReportDTO(
                    financialReportEntity.getFinancialReportId(),
                    financialReportEntity.getName(),
                    financialReportEntity.getAmount(),
                    financialReportEntity.getTotal_expenses(),
                    financialReportEntity.getDescription(),
                    financialReportEntity.getCreatedat(),
                    financialReportEntity.getNote(),
                    financialReportEntity.getCampaignEntity().getCampaignId(),
                    financialReportEntity.getAccountEntity().getAccountId(),
                    financialReportEntity.getCampaignEntity().getName(),
                    financialReportEntity.getAccountEntity().getProfileEntity().getFirstname());
            financialReportDTOS.add(financialReportDTO);
        });
        return financialReportDTOS;
    }
    public List<CampaignDTO> mapToListCampaignDTO(List<CampaignEntity> campaignEntities) {
        List<CampaignDTO> campaignDTOS = new ArrayList<>();
        campaignEntities.stream().forEach(campaignEntity -> {
            CampaignDTO campaignDTO = new CampaignDTO(campaignEntity.getCampaignId(), campaignEntity.getName(),
                    campaignEntity.getStart_date(),
                    campaignEntity.getEnd_date(),
                    campaignEntity.getDescription(),
                    campaignEntity.getTitle(),
                    campaignEntity.getLocation(),
                    campaignEntity.getStatusIssueEntity().getStatusId(),
                    campaignEntity.getStatusIssueEntity().getName(),
                    campaignEntity.isStatus(), campaignEntity.getImage());
            campaignDTOS.add(campaignDTO);
        });
        return campaignDTOS;
    }
    public List<MilestoneDTO> mapToListMilestoneDTO(List<MilestoneEntity> milestoneEntities) {
        List<MilestoneDTO> milestoneDTOS = new ArrayList<>();
        milestoneEntities.stream().forEach( milestoneEntity-> {
            MilestoneDTO milestoneDTO = new MilestoneDTO(
                    milestoneEntity.getMilestoneId(),
                    milestoneEntity.getName(),
                    milestoneEntity.getDescription(),
                    milestoneEntity.getCreate_at(),
                    milestoneEntity.getEnd_date(),
                    milestoneEntity.getCampaignEntity().getCampaignId(),
                    milestoneEntity.getCurrentStatusEntity().getStatusId(),
                    milestoneEntity.getCampaignEntity().getName(),
                    milestoneEntity.getCurrentStatusEntity().getName());

            milestoneDTOS.add(milestoneDTO);
        });
        return milestoneDTOS;
    }
    public List<DonorDTO> mapToListDonorDTO(List<DonorEntity> donorEntities) {
        List<DonorDTO> donorDTOS = new ArrayList<>();
        donorEntities.stream().forEach(donorEntity -> {
            DonorDTO donorDTO = new DonorDTO(
                    donorEntity.getDonorId(),
                    donorEntity.getName(),
                    donorEntity.getAmount(),
                    donorEntity.getDonate_date(),
                    donorEntity.getDescription(),
                    donorEntity.getCampaignEntity().getCampaignId(),
                    donorEntity.getCampaignEntity().getName());

            donorDTOS.add(donorDTO);
        });
        return donorDTOS;
    }
    public ProfileDTO mapToProfileDTO(AccountEntity accountEntity) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstname(accountEntity.getProfileEntity().getFirstname());
        profileDTO.setLastname(accountEntity.getProfileEntity().getLastname());
        profileDTO.setAddress(accountEntity.getProfileEntity().getAddress());
        profileDTO.setEmail(accountEntity.getEmail());
        profileDTO.setAvatar(accountEntity.getProfileEntity().getAvatar());
        profileDTO.setPhone(accountEntity.getPhone());
        profileDTO.setRol(accountEntity.getRoleEntity().getName());
        profileDTO.setDepartment(accountEntity.getDepartmentEntity().getName());
        return profileDTO;
    }

    public CampaignDTO mapCampaignEntityDTO(CampaignEntity campaignEntity) {
        CampaignDTO campaignDTO = new CampaignDTO(campaignEntity.getCampaignId(),
                campaignEntity.getName(),
                campaignEntity.getStart_date(),
                campaignEntity.getEnd_date(),
                campaignEntity.getDescription(),
                campaignEntity.getTitle(),
                campaignEntity.getLocation(),
                campaignEntity.getStatusIssueEntity().getStatusId(),
                campaignEntity.getStatusIssueEntity().getName(),
                campaignEntity.isStatus(),campaignEntity.getImage());
        return campaignDTO;
    }

    public List<GeneralReportDTO> mapToListGeneralReportDTO(List<GeneralReportEntity> generalReportEntities) {
        List<GeneralReportDTO> generalReportDTOS = new ArrayList<>();
        generalReportEntities.stream().forEach(generalReportEntity -> {
            GeneralReportDTO generalReportDTO = new GeneralReportDTO(generalReportEntity.getGeneralReportId(), generalReportEntity.getAttachment(),
                    generalReportEntity.getCreated_at(),
                    generalReportEntity.getCampaignEntity().getCampaignId(),
                    generalReportEntity.getCurrentStatusEntity().getStatusId(),
                    generalReportEntity.getCampaignEntity().getName(),
                    generalReportEntity.getCurrentStatusEntity().getName());
            generalReportDTOS.add(generalReportDTO);
        });
        return generalReportDTOS;
    }

    public List<TaskReportDTO> mapToListTaskReportDTO(List<TaskReportEntity> taskReportEntities) {
        List<TaskReportDTO> taskReportDTOS = new ArrayList<>();
        taskReportEntities.stream().forEach(taskReportEntity -> {
            TaskReportDTO taskReportDTO = new TaskReportDTO(
                    taskReportEntity.getTaskReport_Id(),
                    taskReportEntity.getName(),
                    taskReportEntity.getDescription(),
                    taskReportEntity.getTitle(),
                    taskReportEntity.getDue_date(),
                    taskReportEntity.getNote(),
                    taskReportEntity.getAccountEntity().getAccountId(),
                    taskReportEntity.getCampaignEntity().getCampaignId(),
                    taskReportEntity.getCurrentStatusEntity().getStatusId(),
                    taskReportEntity.getAccountEntity().getProfileEntity().getLastname() + " " + taskReportEntity.getAccountEntity().getProfileEntity().getFirstname(),
                    taskReportEntity.getCampaignEntity().getName(),
                    taskReportEntity.getCurrentStatusEntity().getName());
            taskReportDTOS.add(taskReportDTO);
        });
        return taskReportDTOS;
    }


    public CampaignEntity mapCampaignDTOToEntity(CampaignDTO campaignDTO) {
        CampaignEntity campaignEntity = new CampaignEntity();
        campaignEntity.setCampaignId(campaignDTO.getCampaignId());
        campaignEntity.setName(campaignDTO.getName());
        campaignEntity.setStart_date(campaignDTO.getStart_date());
        campaignEntity.setEnd_date(campaignDTO.getEnd_date());
        campaignEntity.setDescription(campaignDTO.getDescription());
        campaignEntity.setTitle(campaignDTO.getTitle());
        campaignEntity.setLocation(campaignDTO.getLocation());
        campaignEntity.getStatusIssueEntity().getStatusId();
        return campaignEntity;
    }

    public List<IssueDTO> maptoListIsssueDTO(List<IssueEntity> issueEntities) {
        List<IssueDTO> issueDTOS = new ArrayList<>();
        issueEntities.stream().forEach(issueEntity -> {
            IssueDTO issueDTO = new IssueDTO(
                    issueEntity.getIssueId(),
                    issueEntity.getTitle(),
                    issueEntity.getDescription(),
                    issueEntity.getAssignee(),
                    issueEntity.getStart_date(),
                    issueEntity.getDue_date(),
                    issueEntity.getAccountEntity().getAccountId(),
                    issueEntity.getTaskReportEntity().getTaskReport_Id(),
                    issueEntity.getCurrentStatusEntity().getStatusId(),
                    issueEntity.getAccountEntity().getProfileEntity().getFirstname(),
                    issueEntity.getTaskReportEntity().getName(),
                    issueEntity.getCurrentStatusEntity().getName());
            issueDTOS.add(issueDTO);
        });
        return issueDTOS;
    }

    public List<StoryDTO> mapToListStoryDTO(List<StoryEntity> storyEntities) {
        List<StoryDTO> storyDTOS = new ArrayList<>();
        storyEntities.stream().forEach(storyEntity -> {
            StoryDTO storyDTO = new StoryDTO(
                    storyEntity.getStoryId(),
                    storyEntity.getName(),
                    storyEntity.getContent(),
                    storyEntity.getTitle(),
                    storyEntity.getCreated_at(),storyEntity.getImage(),
                    storyEntity.getCampaignEntity().getCampaignId(),
                    storyEntity.getCampaignEntity().getName());
            storyDTOS.add(storyDTO);
        });
        return storyDTOS;
    }
    public List<AccountDTO> mapToListAccountDTO(List<AccountEntity> accountEntities) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        accountEntities.stream().forEach(accountEntity -> {
            AccountDTO accountDTO = new AccountDTO(
                    accountEntity.getAccountId(),
                    accountEntity.getProfileEntity().getFirstname(),
                    accountEntity.getProfileEntity().getLastname(),
                    accountEntity.getProfileEntity().getAvatar(),
                    accountEntity.getProfileEntity().getAddress(),
                    accountEntity.getEmail(),
                    accountEntity.getPhone(),
                    accountEntity.getPassword(),
                    accountEntity.getRoleEntity().getRoleId(),
                    accountEntity.getDepartmentEntity().getDepartmentId(),
                    accountEntity.getRoleEntity().getName(),
                    accountEntity.getStatus(),
                    accountEntity.getDepartmentEntity().getName(),
                    accountEntity.getPassword());
            accountDTOS.add(accountDTO);
        });
        return accountDTOS;
    }

    public List<ResponseNewDTO> mapToListNewDTO(List<NewEntity> newEntities) {
        List<ResponseNewDTO> newDTOS = new ArrayList<>();
        newEntities.forEach(newEntity -> {
            ResponseNewDTO newDTO = new ResponseNewDTO(
                    newEntity.getNewId(),
                    newEntity.getTitle(),
                    newEntity.getContent(),
                    newEntity.getCreated_at(),
                    newEntity.getImage()
            );
            newDTOS.add(newDTO);
        });
        return newDTOS;
    }

    public List<RequestVolunteerDTO> mapToListRequestVolunteerDTO(List<RequestVolunteerEntity> requestVolunteerEntities) {
        List<RequestVolunteerDTO> requestVolunteerDTOS = new ArrayList<>();
        requestVolunteerEntities.forEach(requestVolunteerEntity -> {
            RequestVolunteerDTO requestVolunteerDTO = new RequestVolunteerDTO(
                    requestVolunteerEntity.getRequestVolunteerId(),
                    requestVolunteerEntity.getName(),
                    requestVolunteerEntity.getEmail(),
                    requestVolunteerEntity.getPhone(),
                    requestVolunteerEntity.getDate_of_birth(),
                    requestVolunteerEntity.getAddress(),
                    requestVolunteerEntity.getReason(),
                    requestVolunteerEntity.getCampaignEntity().getCampaignId(),
                    requestVolunteerEntity.getCampaignEntity().getName(),
                    requestVolunteerEntity.isStatus()
            );
            requestVolunteerDTOS.add(requestVolunteerDTO);
        });
        return requestVolunteerDTOS;
    }

    public CurrentStatusDTO mapCurrentStatusToDTO(CurrentStatusEntity currentStatusEntity){
        CurrentStatusDTO currentStatusDTO = new CurrentStatusDTO(currentStatusEntity.getStatusId(), currentStatusEntity.getName(), currentStatusEntity.getDescription());
        return currentStatusDTO;
    }

    public CurrentStatusEntity mapDTOtoCurrentStatusEntity(CurrentStatusDTO currentStatusDTO) {
        CurrentStatusEntity currentStatusEntity = new CurrentStatusEntity(currentStatusDTO.getStatusId(),
                currentStatusDTO.getName(), currentStatusDTO.getDescription());
        return currentStatusEntity;
    }

    public List<RequestVolunteerDTO> mapToListRequestVolunteer(List<RequestVolunteerEntity> requestVolunteerEntities) {
        List<RequestVolunteerDTO> requestVolunteerDTOS = new ArrayList<>();
        requestVolunteerEntities.stream().forEach(requestVolunteerEntity -> {
            RequestVolunteerDTO requestVolunteerDTO   = new RequestVolunteerDTO(
                    requestVolunteerEntity.getRequestVolunteerId(),
                    requestVolunteerEntity.getName(),
                    requestVolunteerEntity.getEmail(),
                    requestVolunteerEntity.getPhone(),
                    requestVolunteerEntity.getDate_of_birth(),
                    requestVolunteerEntity.getAddress(),
                    requestVolunteerEntity.getReason(),
                    requestVolunteerEntity.getCampaignEntity().getCampaignId(),
                    requestVolunteerEntity.getCampaignEntity().getName(),
                    requestVolunteerEntity.isStatus()
);
            requestVolunteerDTOS.add(requestVolunteerDTO);
        });
        return requestVolunteerDTOS;
    }

    public List<StoryDTO> mapToListStory(List<StoryEntity> storyEntities) {
        List<StoryDTO> storyDTOS = new ArrayList<>();
        storyEntities.stream().forEach(storyEntity -> {
            StoryDTO storyDTO   = new StoryDTO(
                    storyEntity.getStoryId(),
                    storyEntity.getName(),
                    storyEntity.getContent(),
                    storyEntity.getTitle(),
                    storyEntity.getCreated_at(),
                    storyEntity.getImage(),
                    storyEntity.getCampaignEntity().getCampaignId(),
                    storyEntity.getCampaignEntity().getName()
            );
            storyDTOS.add(storyDTO);
        });
        return storyDTOS;
    }

    public List<MediaDTO> mapToListMediaDTO(List<MediaCampaignEntity> mediaEntities) {
        List<MediaDTO> mediaDTOS = new ArrayList<>();
        mediaEntities.forEach(mediaEntity -> {
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO.setIdMedia(mediaEntity.getMediaId());
            mediaDTO.setImage(mediaEntity.getImage());
            mediaDTO.setVideo(mediaEntity.getVideo());
            mediaDTOS.add(mediaDTO);
        });
        return mediaDTOS;
    }
}

