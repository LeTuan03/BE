package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.IssueDTO;
import com.example.volunteer_campaign_management.dtos.RequestVolunteerDTO;

import java.util.List;
import java.util.Optional;
public interface RequestVolunteerService {
    List<RequestVolunteerDTO> getAllRequestVolunteer();
    RequestVolunteerDTO registerVolunteer(RequestVolunteerDTO requestVolunteerDTO);

    RequestVolunteerDTO updateVolunteerStatus(int volunteerId, RequestVolunteerDTO requestVolunteerDTO);
    List<RequestVolunteerDTO> searchRequestVolunteer(Optional<String> query);
}
