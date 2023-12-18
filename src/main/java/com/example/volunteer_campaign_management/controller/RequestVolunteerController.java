package com.example.volunteer_campaign_management.controller;


import com.example.volunteer_campaign_management.dtos.IssueDTO;
import com.example.volunteer_campaign_management.dtos.RequestVolunteerDTO;
import com.example.volunteer_campaign_management.services.RequestVolunteerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class RequestVolunteerController {
    @Autowired
    private final RequestVolunteerService requestVolunteerService;

    @GetMapping("/RequestVolunteer/list")
    public List<RequestVolunteerDTO> getAllRequestVolunteer() {
        return requestVolunteerService.getAllRequestVolunteer();
    }

    @PostMapping("/RequestVolunteer/create")
    public RequestVolunteerDTO createRequestVolunteer(@RequestBody @Valid RequestVolunteerDTO requestVolunteerDTO) {
        return requestVolunteerService.registerVolunteer(requestVolunteerDTO);
    }

    @PutMapping("/RequestVolunteer/updateVolunteerStatus/{id}")
    public RequestVolunteerDTO updateVolunteerStatus(@PathVariable("id") @Valid int volunteerId, @RequestBody RequestVolunteerDTO requestVolunteerDTO) {

        return requestVolunteerService.updateVolunteerStatus(volunteerId, requestVolunteerDTO);
    }

    @GetMapping (value = {"/RequestVolunteer/searchRequestVolunteer","/RequestVolunteer/searchRequestVolunteer/{query}"})
    public java.util.List<RequestVolunteerDTO> searchRequestVolunteer(@PathVariable(value = "query") Optional<String> query){
        return requestVolunteerService.searchRequestVolunteer(query);
    }
}

