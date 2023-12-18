package com.example.volunteer_campaign_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "issues")
public class IssueEntity {
    @Id
    @Column(name = "issue_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issueId;
    private String title;
    private String description;
    private String assignee;
    private Timestamp start_date;
    private Timestamp due_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private AccountEntity accountEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskreport_id", referencedColumnName = "taskreport_id")
    private TaskReportEntity taskReportEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_Status_id", referencedColumnName = "current_Status_id")
    private CurrentStatusEntity currentStatusEntity;
}
