package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.MilestoneDTO;

import java.util.List;
import java.util.Optional;

public interface MilestoneService {
    List<MilestoneDTO> getAllMilestone();
    MilestoneDTO updateMilestone(int milestoneID, MilestoneDTO milestoneRequest);

    MilestoneDTO createMilestone(MilestoneDTO milestoneRequest);

    boolean deleteMilestone(int milestonID);

    List<MilestoneDTO> searchMilestone(Optional<String> query);


    MilestoneDTO getMilestoneById(int milestoneID);
}
