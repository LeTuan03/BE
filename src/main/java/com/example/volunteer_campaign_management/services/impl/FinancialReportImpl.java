package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.FinancialReportDTO;
import com.example.volunteer_campaign_management.entities.AccountEntity;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.FinancialReportEntity;
import com.example.volunteer_campaign_management.entities.ProfileEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.AccountRepository;
import com.example.volunteer_campaign_management.repositories.CampaignRepository;
import com.example.volunteer_campaign_management.repositories.FinancialReportRepository;
import com.example.volunteer_campaign_management.services.FinancialReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

;

@Service
@AllArgsConstructor
public class FinancialReportImpl implements FinancialReportService {
    @Autowired
    private final FinancialReportRepository financialReportRepository;
    private final AccountRepository accountRepository;
    private final CampaignRepository campainRepository;
    private final MapperUtil mapperUtil;

    @Override
    public List<FinancialReportDTO> getAllFinancialReport() {
        try {
            List<FinancialReportEntity> financialReportEntities = financialReportRepository.findAll();
            List<FinancialReportDTO> financialReportDTOS = new ArrayList<>();
            financialReportEntities.stream().forEach(financialReportEntity -> {
                FinancialReportDTO financialReportDTO = new FinancialReportDTO(financialReportEntity.getFinancialReportId(),
                        financialReportEntity.getName(), financialReportEntity.getAmount(), financialReportEntity.getTotal_expenses(),
                        financialReportEntity.getDescription(), financialReportEntity.getCreatedat(),
                        financialReportEntity.getNote(), financialReportEntity.getCampaignEntity().getCampaignId(),
                        financialReportEntity.getAccountEntity().getAccountId(),
                        financialReportEntity.getCampaignEntity().getName(), financialReportEntity.getAccountEntity().getProfileEntity().getFirstname());
                financialReportDTOS.add(financialReportDTO);

            });
            return financialReportDTOS;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public FinancialReportDTO createFinancialReport(FinancialReportDTO financialReportDTO) {
        try {
            FinancialReportEntity financialReportEntity = new FinancialReportEntity();
            financialReportEntity.setFinancialReportId(financialReportDTO.getFinancialreport_id());
            financialReportEntity.setName(financialReportDTO.getName());
            financialReportEntity.setAmount(financialReportDTO.getAmount());
            financialReportEntity.setTotal_expenses(financialReportDTO.getTotal_expenses());
            financialReportEntity.setDescription(financialReportDTO.getDescription());
            financialReportEntity.setCreatedat(Timestamp.valueOf(LocalDateTime.now()));
            financialReportEntity.setNote(financialReportDTO.getNote());
            CampaignEntity campaignEntity = campainRepository.getOne(financialReportDTO.getCampaign_id());
            financialReportEntity.setCampaignEntity(campaignEntity);
            AccountEntity accountEntity = accountRepository.getOne(financialReportDTO.getUser_id());
            financialReportEntity.setAccountEntity(accountEntity);
            financialReportRepository.save(financialReportEntity);
            return financialReportDTO;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }


    @Override
    public FinancialReportDTO updateFinancialReport(int financialReportID, FinancialReportDTO financialReportDTO) {
        try {
            FinancialReportEntity financialReportEntity = financialReportRepository.getOne(financialReportID);
            financialReportEntity.setName(financialReportDTO.getName());
            financialReportEntity.setAmount(financialReportDTO.getAmount());
            financialReportEntity.setTotal_expenses(financialReportDTO.getTotal_expenses());
            financialReportEntity.setDescription(financialReportDTO.getDescription());
            financialReportEntity.setCreatedat(financialReportDTO.getCreated_at());
            financialReportEntity.setNote(financialReportDTO.getNote());
            CampaignEntity campaignEntity = campainRepository.getOne(financialReportDTO.getCampaign_id());
            financialReportEntity.setCampaignEntity(campaignEntity);
            AccountEntity accountEntity = accountRepository.getOne(financialReportDTO.getUser_id());
            financialReportEntity.setAccountEntity(accountEntity);
            financialReportRepository.save(financialReportEntity);
            return financialReportDTO;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean deleteFinancialReport(int financialReportID) {
        try {
            financialReportRepository.deleteById(financialReportID);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public FinancialReportDTO getFinancialReportById(int financialReportID) {
        try {
            FinancialReportEntity financialReportEntity = financialReportRepository.findById(financialReportID).get();
            FinancialReportDTO reportDTO = new FinancialReportDTO();
            reportDTO.setName(financialReportEntity.getName());
            reportDTO.setAmount(financialReportEntity.getAmount());
            reportDTO.setTotal_expenses(financialReportEntity.getTotal_expenses());
            reportDTO.setDescription(financialReportEntity.getDescription());
            reportDTO.setCreated_at(financialReportEntity.getCreatedat());
            reportDTO.setNote(financialReportEntity.getNote());
            reportDTO.setCampaign_id(financialReportEntity.getCampaignEntity().getCampaignId());
            reportDTO.setUser_id(financialReportEntity.getAccountEntity().getAccountId());
            reportDTO.setCampaign_name(financialReportEntity.getCampaignEntity().getName());
            reportDTO.setUser_name(financialReportEntity.getAccountEntity().getProfileEntity().getFirstname());
            reportDTO.setFinancialreport_id(financialReportID);
            return reportDTO;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<FinancialReportDTO> searchFinancialReport(Optional<String> query) {
        try {
            List<FinancialReportEntity> financialReportDTOS = new ArrayList<>();
            if (!query.isPresent()) {
                return getAllFinancialReport();
            }
            {
                financialReportDTOS = financialReportRepository.findByNameContainingIgnoreCaseOrAmountContainingIgnoreCaseOrDescriptionContainsIgnoreCaseOrNoteContainsIgnoreCase(query, query, query, query);
                if (financialReportDTOS.isEmpty()) {
                    List<CampaignEntity> campaignEntities = this.campainRepository.findByNameContainsIgnoreCase(query);
                    for (CampaignEntity campaignEntity : campaignEntities) {
                        financialReportDTOS.addAll(this.financialReportRepository.findByCampaignEntity(campaignEntity));
                    }
                }

                List<AccountEntity> accountEntities = this.accountRepository.findByProfileEntityFirstnameContainingIgnoreCase(query);
                for (AccountEntity accountEntity : accountEntities) {
                    financialReportDTOS.addAll(this.financialReportRepository.findByAccountEntity(accountEntity));
                }
            }
            return mapperUtil.mapToListFinancialReportDTO(financialReportDTOS);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}