package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.AccountEntity;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.FinancialReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialReportRepository extends JpaRepository<FinancialReportEntity,Integer> {
    @Override
    List<FinancialReportEntity> findAll();
    List<FinancialReportEntity> findByNameContainingIgnoreCaseOrAmountContainingIgnoreCaseOrDescriptionContainsIgnoreCaseOrNoteContainsIgnoreCase
    (Optional<String> query, Optional<String> query1, Optional<String> query2, Optional<String> query3);
    List<FinancialReportEntity> findByCampaignEntity(CampaignEntity  campaignEntity);
    List<FinancialReportEntity> findByAccountEntity(AccountEntity accountEntity);

}