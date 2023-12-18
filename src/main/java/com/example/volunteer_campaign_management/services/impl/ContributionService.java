package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.entities.ContributionsEntity;
import com.example.volunteer_campaign_management.repositories.ContributionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContributionService {
    @Autowired
    private ContributionsRepository repository;
    public Page<ContributionsEntity> findAll(Pageable pageable) {
        return repository.findContributionsEntities(pageable);
    }
}
