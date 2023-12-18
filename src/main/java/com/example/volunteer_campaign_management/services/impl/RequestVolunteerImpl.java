package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.IssueDTO;
import com.example.volunteer_campaign_management.dtos.RequestVolunteerDTO;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.IssueEntity;
import com.example.volunteer_campaign_management.entities.RequestVolunteerEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.CampaignRepository;
import com.example.volunteer_campaign_management.repositories.RequestVolunteerRepository;
import com.example.volunteer_campaign_management.services.RequestVolunteerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestVolunteerImpl implements RequestVolunteerService {
    private final CampaignRepository campainRepository;
    private final RequestVolunteerRepository requestVolunteerRepository;
    private final MapperUtil mapperUtil;

    @Override
    public List<RequestVolunteerDTO> getAllRequestVolunteer() {
        try{

            List<RequestVolunteerEntity> requestVolunteerEntities =requestVolunteerRepository.findAll();
            List<RequestVolunteerDTO> requestVolunteerDTOS = new ArrayList<>();
            requestVolunteerEntities.stream().forEach(requestVolunteerEntity ->{
                RequestVolunteerDTO requestVolunteerDTO = new RequestVolunteerDTO(
                        requestVolunteerEntity.getRequestVolunteerId(),requestVolunteerEntity.getName(),requestVolunteerEntity.getEmail(),
                        requestVolunteerEntity.getPhone(),requestVolunteerEntity.getDate_of_birth(),requestVolunteerEntity.getAddress(),requestVolunteerEntity.getReason(),
                     requestVolunteerEntity.getCampaignEntity().getCampaignId(),requestVolunteerEntity.getCampaignEntity().getName(),requestVolunteerEntity.isStatus());
                requestVolunteerDTOS.add(requestVolunteerDTO);
            });
            return requestVolunteerDTOS;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public RequestVolunteerDTO registerVolunteer(RequestVolunteerDTO requestVolunteerDTO) {
        try{
            RequestVolunteerEntity requestVolunteerEntity = new RequestVolunteerEntity();
            requestVolunteerEntity.setRequestVolunteerId(requestVolunteerDTO.getId());
            requestVolunteerEntity.setName(requestVolunteerDTO.getName());
            requestVolunteerEntity.setEmail(requestVolunteerDTO.getEmail());
            requestVolunteerEntity.setPhone(requestVolunteerDTO.getPhone());
            requestVolunteerEntity.setDate_of_birth(requestVolunteerDTO.getDate_of_birth());
            requestVolunteerEntity.setAddress(requestVolunteerDTO.getAddress());
            requestVolunteerEntity.setReason(requestVolunteerDTO.getReason());
            CampaignEntity campaignEntity= campainRepository.getOne(requestVolunteerDTO.getCampain_id());
            requestVolunteerEntity.setCampaignEntity(campaignEntity);
            requestVolunteerRepository.save(requestVolunteerEntity);
            return requestVolunteerDTO;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public RequestVolunteerDTO updateVolunteerStatus(int volunteerId, RequestVolunteerDTO requestVolunteerDTO) {

        RequestVolunteerEntity volunteer = requestVolunteerRepository.findById(volunteerId).orElse(null);
        try {
            volunteer.setStatus(requestVolunteerDTO.isStatus());
            requestVolunteerRepository.save(volunteer);
            return requestVolunteerDTO;
        } catch (Exception e) {
            e.getMessage();
        }
            return null;
    }

    @Override
    public List<RequestVolunteerDTO> searchRequestVolunteer(Optional<String> query) {
        List<RequestVolunteerEntity> requestVolunteerDTOS = new ArrayList<>();
        try{
            if (!query.isPresent()) {
                return getAllRequestVolunteer();
            }
            else {
                requestVolunteerDTOS = requestVolunteerRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrAddressContainingIgnoreCaseOrReasonContainsIgnoreCase(query,query, query,query);

            }
        } catch (Exception e) {
            e.getMessage();
        }
        return mapperUtil.mapToListRequestVolunteerDTO(requestVolunteerDTOS);
    }
}



