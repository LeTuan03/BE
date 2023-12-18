package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.DonorDTO;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.DonorEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.CampaignRepository;
import com.example.volunteer_campaign_management.repositories.DonorRepository;
import com.example.volunteer_campaign_management.services.DonorService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonorImpl implements DonorService {
    private final DonorRepository donorRepository;
    private final CampaignRepository campaignRepository;
    private final MapperUtil mapperUtil;
    //private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("image"));
    @JsonIgnore
    private final String fileUpload = "D:\\CasptonProject\\volunteer-campaign-management-system\\image\\imageUser";

    @Override
    public List<DonorDTO> getAllDonor() {
        try{
        List<DonorEntity> donorEntities = donorRepository.findAllSort();
            List<DonorDTO> donorDTOS = new ArrayList<>();
            donorEntities.stream().forEach(donorEntity -> {
                DonorDTO donorDTO = new DonorDTO(donorEntity.getDonorId(),
                        donorEntity.getName(), donorEntity.getAmount(),
                        donorEntity.getDonate_date(), donorEntity.getDescription(),donorEntity.getCampaignEntity().getCampaignId(), donorEntity.getCampaignEntity().getName());

                donorDTOS.add(donorDTO);
            });
        return donorDTOS;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public DonorDTO createDonor(DonorDTO donorDTO) {
        try{
            DonorEntity donorEntity = new DonorEntity();
            donorEntity.setName(donorDTO.getName());
            donorEntity.setAmount(donorDTO.getAmount());
            donorEntity.setDonate_date(donorDTO.getDonate_date());
            donorEntity.setDescription(donorDTO.getDescription());
            CampaignEntity campaignEntity = campaignRepository.getOne(donorDTO.getCampaign_id());
            donorEntity.setCampaignEntity(campaignEntity);
            donorRepository.save(donorEntity);
            return donorDTO;
        }  catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public DonorDTO updateDonor(int donorID,DonorDTO donorDTO) {
        try {
            DonorEntity donorEntity = donorRepository.getOne(donorID);
            donorEntity.setName(donorDTO.getName());
            donorEntity.setAmount(donorDTO.getAmount());
            donorEntity.setDonate_date(donorDTO.getDonate_date());
            donorEntity.setDescription(donorDTO.getDescription());
            CampaignEntity campaignEntity = campaignRepository.getOne(donorDTO.getCampaign_id());
            donorEntity.setCampaignEntity(campaignEntity);
            donorRepository.save(donorEntity);
            return donorDTO;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    @Override
    public DonorDTO getDonorById(int donorID) {
        try{
            DonorEntity donorEntity = donorRepository.findById(donorID).get();
            DonorDTO donorDTO = new DonorDTO();
            donorDTO.setName(donorEntity.getName());
            donorDTO.setAmount(donorEntity.getAmount());
            donorDTO.setDonate_date(donorEntity.getDonate_date());
            donorDTO.setDescription(donorEntity.getDescription());
            donorDTO.setCampaign_id(donorEntity.getCampaignEntity().getCampaignId());
            donorDTO.setCampaign_name(donorEntity.getCampaignEntity().getName());
            donorDTO.setDonor_id(donorID);
            return donorDTO;
        }  catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<DonorDTO> searchByName(String name) {
        try{
            List<DonorEntity> donorEntities = donorRepository.searchByName(name);
            List<DonorDTO> donorDTOS = new ArrayList<>();
            donorEntities.stream().forEach(donorEntity -> {
                DonorDTO donorDTO = new DonorDTO(donorEntity.getDonorId(),
                        donorEntity.getName(), donorEntity.getAmount(),
                        donorEntity.getDonate_date(), donorEntity.getDescription(),donorEntity.getCampaignEntity().getCampaignId(), donorEntity.getCampaignEntity().getName());

                donorDTOS.add(donorDTO);
            });
            return donorDTOS;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean deleteDonor(int donorID) {

        try {
            donorRepository.deleteById(donorID);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public List<DonorDTO> searchDonor(Optional<String> query) {
        try{
        List<DonorEntity> donorDTOS = new ArrayList<>();
            if (!query.isPresent()) {
                return getAllDonor();
            }
        else {
            donorDTOS = donorRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
            if (donorDTOS.isEmpty()) {
                List<CampaignEntity> campaignEntities = this.campaignRepository.findByNameContainsIgnoreCase(query);
                for (CampaignEntity campaignEntity : campaignEntities) {
                    donorDTOS.addAll(this.donorRepository.findByCampaignEntity(campaignEntity));
                }
            }
        }
        return mapperUtil.mapToListDonorDTO(donorDTOS);
    } catch (Exception e) {
        e.getMessage();
    }
        return null;
}
}



