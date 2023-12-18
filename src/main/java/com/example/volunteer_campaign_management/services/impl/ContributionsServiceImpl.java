package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.entities.ContributionsEntity;
import com.example.volunteer_campaign_management.repositories.ContributionsRepository;
import com.example.volunteer_campaign_management.services.ContributionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ContributionsServiceImpl implements ContributionsService {
    private final ContributionsRepository contributionsRepository;

    @Autowired
    public ContributionsServiceImpl(ContributionsRepository contributionsRepository) {
        this.contributionsRepository = contributionsRepository;
    }

    @Override
    public Page<ContributionsEntity> findContributionsByName(String name, Pageable pageable) {
        return contributionsRepository.findByNameContri(name, pageable);
    }

    @Override
    public Page<ContributionsEntity> findAll(Pageable pageable) {
        return contributionsRepository.findContributionsEntities(pageable);
    }
}


