package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.entities.AccountEntity;
import com.example.volunteer_campaign_management.entities.CurrentStatusEntity;
import com.example.volunteer_campaign_management.entities.IssueEntity;
import com.example.volunteer_campaign_management.entities.TaskReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Integer> {


    List<IssueEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssigneeContainingIgnoreCase(Optional<String> query1, Optional<String> query2, Optional<String> query3);

    Collection<? extends IssueEntity> findByTaskReportEntity(TaskReportEntity taskReportEntity);

    Collection<? extends IssueEntity> findByAccountEntity(AccountEntity accountEntity);

    Collection<? extends IssueEntity> findByCurrentStatusEntity(CurrentStatusEntity currentStatusEntity);
}
