package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.MilestoneDTO;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.MilestoneEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.CampaignRepository;
import com.example.volunteer_campaign_management.repositories.CurrentStatusRepository;
import com.example.volunteer_campaign_management.repositories.MilestoneRepository;
import com.example.volunteer_campaign_management.services.MilestoneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MilestoneImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final CampaignRepository campainRepository;
    private final CurrentStatusRepository currentStatusRepository;
    private final MapperUtil mapperUtil;

    @Override
    public List<MilestoneDTO> getAllMilestone() {
        try{
        List<MilestoneEntity> milestoneEntities = milestoneRepository.findAll();
        List<MilestoneDTO> milestoneDTOS = new ArrayList<>();
            milestoneEntities.stream().forEach(milestoneEntity -> {
                MilestoneDTO milestoneDTO = new MilestoneDTO(milestoneEntity.getMilestoneId(),
                        milestoneEntity.getName(), milestoneEntity.getDescription(),
                        milestoneEntity.getEnd_date(), milestoneEntity.getCreate_at(),
                        milestoneEntity.getCampaignEntity().getCampaignId(),
                        milestoneEntity.getCurrentStatusEntity().getStatusId(),
                        milestoneEntity.getCampaignEntity().getName(), milestoneEntity.getCurrentStatusEntity().getName());
                milestoneDTOS.add(milestoneDTO);
        });
        return milestoneDTOS;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    @Transactional
    public MilestoneDTO updateMilestone(int milestoneID, MilestoneDTO milestoneDTO) {
        try {
            MilestoneEntity milestoneEntity = milestoneRepository.getOne(milestoneID);
            milestoneEntity.setName(milestoneDTO.getName());
            milestoneEntity.setDescription(milestoneDTO.getDescription());
            milestoneEntity.setCreate_at(milestoneDTO.getCreate_at());
            milestoneEntity.setEnd_date(milestoneDTO.getEnd_date());

            CampaignEntity campaignEntity = campainRepository.getOne(milestoneDTO.getCampaign_id());
            milestoneEntity.setCampaignEntity(campaignEntity);

            CurrentStatusEntity currentStatusEntity = currentStatusRepository.getOne(milestoneDTO.getCurrent_Status_id());
            milestoneEntity.setCurrentStatusEntity(currentStatusEntity);

            milestoneRepository.save(milestoneEntity);
            return milestoneDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public MilestoneDTO getMilestoneById(int milestoneID) {
        try {
            MilestoneEntity milestoneEntity = milestoneRepository.findById(milestoneID).get();
            MilestoneDTO milestoneDTO = new MilestoneDTO();
            milestoneDTO.setName(milestoneEntity.getName());
            milestoneDTO.setDescription(milestoneEntity.getDescription());
            milestoneDTO.setCreate_at(milestoneEntity.getCreate_at());
            milestoneDTO.setEnd_date(milestoneEntity.getEnd_date());
            milestoneDTO.setCampain_name(milestoneEntity.getCampaignEntity().getName());
            milestoneDTO.setCampaign_id(milestoneEntity.getCampaignEntity().getCampaignId());
            milestoneDTO.setCurrent_Status_id(milestoneEntity.getCurrentStatusEntity().getStatusId());
            milestoneDTO.setCurrent_status_name(milestoneEntity.getCurrentStatusEntity().getName());
            milestoneDTO.setMilestione_id(milestoneID);
            return milestoneDTO;
        } catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public MilestoneDTO createMilestone(MilestoneDTO milestoneDTO) {
        try {
            MilestoneEntity milestoneEntity = new MilestoneEntity();
            milestoneEntity.setMilestoneId(milestoneEntity.getMilestoneId());
            milestoneEntity.setName(milestoneDTO.getName());
            milestoneEntity.setDescription(milestoneDTO.getDescription());
            milestoneEntity.setCreate_at(milestoneDTO.getCreate_at());
            milestoneEntity.setEnd_date(milestoneDTO.getEnd_date());
            CampaignEntity campaignEntity = campainRepository.getOne(milestoneDTO.getCampaign_id());
            milestoneEntity.setCampaignEntity(campaignEntity);
            CurrentStatusEntity currentStatusEntity = currentStatusRepository.getOne(milestoneDTO.getCurrent_Status_id());
            milestoneEntity.setCurrentStatusEntity(currentStatusEntity);
            milestoneRepository.save(milestoneEntity);
            return milestoneDTO;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean deleteMilestone(int milestonID) {
        try {
            milestoneRepository.deleteById(milestonID);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public List<MilestoneDTO> searchMilestone(Optional<String> query) {
        List<MilestoneEntity> milestoneEntities = new ArrayList<>();

                try {
                    if (!query.isPresent()) {
                        return getAllMilestone();
                    }
                  else {
                        milestoneEntities = milestoneRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);

                        List<CampaignEntity> campaignEntities = this.campainRepository.findByNameContainsIgnoreCase(query);
                        for (CampaignEntity campaignEntity : campaignEntities) {
                            milestoneEntities.addAll(this.milestoneRepository.findByCampaignEntity(campaignEntity));
                        }
                        List<CurrentStatusEntity> currentStatusEntities = this.currentStatusRepository.findByNameContainingIgnoreCase(query);
                        for (CurrentStatusEntity currentStatusEntity : currentStatusEntities) {
                            milestoneEntities.addAll(this.milestoneRepository.findByCurrentStatusEntity(currentStatusEntity));
                        }
                    }
                }
                catch (Exception e){
                    e.getMessage();
                }

        return mapperUtil.mapToListMilestoneDTO(milestoneEntities);
    }
    }

