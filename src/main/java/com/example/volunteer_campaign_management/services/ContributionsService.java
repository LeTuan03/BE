package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.entities.ContributionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContributionsService {
    Page<ContributionsEntity> findContributionsByName(String name, Pageable pageable);
    Page<ContributionsEntity> findAll(Pageable pageable);
}
