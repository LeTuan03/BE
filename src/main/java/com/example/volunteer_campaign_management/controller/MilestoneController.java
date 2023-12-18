package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.MilestoneDTO;
import com.example.volunteer_campaign_management.services.MilestoneService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class MilestoneController {
    @Autowired
    private final MilestoneService milestoneService;
    @GetMapping("/milestone/list")
    public List<MilestoneDTO> getAllMilestone() {
        return milestoneService.getAllMilestone();
    }
    @PutMapping("/milestone/update/{id}")
    public MilestoneDTO updateMilestone(@PathVariable(value = "id") @Valid int milestoneID, @RequestBody MilestoneDTO milestoneRequest) {
        return milestoneService.updateMilestone(milestoneID,milestoneRequest);
    }
    @GetMapping("/milestone/{id}")
    public MilestoneDTO getMilestoneById(@PathVariable(value = "id") int milestoneID) {
        return milestoneService.getMilestoneById(milestoneID);
    }
    @PostMapping("/milestone/create")
    public MilestoneDTO createMilestone(@RequestBody @Valid MilestoneDTO MilestoneRequest){
        return milestoneService.createMilestone(MilestoneRequest);
    }
    @DeleteMapping("/milestone/delete/{id}")
    public boolean deleteMilestone(@PathVariable(value = "id") int milestonID) {
        return milestoneService.deleteMilestone(milestonID);
    }
    @GetMapping (value = {"/milestone/search","/milestone/search/{query}"})
    public List<MilestoneDTO> searchMilestone(@PathVariable(value = "query") Optional<String> query){
        return milestoneService.searchMilestone(query);
    }

}