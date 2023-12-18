package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.TaskReportDTO;

import java.util.List;
import java.util.Optional;

public interface TaskReportService {
    TaskReportDTO createNewTaskReport(TaskReportDTO taskReportDTO);
    List<TaskReportDTO> getAllTaskReport();
    TaskReportDTO getTaskReportById(int taskReportId);

    List<TaskReportDTO> searchTaskReport(Optional<String> query);
    TaskReportDTO updateTaskReport(int taskReportId, TaskReportDTO taskReportDTO);
}
