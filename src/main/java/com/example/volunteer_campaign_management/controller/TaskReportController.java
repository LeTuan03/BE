package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.TaskReportDTO;
import com.example.volunteer_campaign_management.services.TaskReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class TaskReportController {
    private final TaskReportService taskReportService;

    @PostMapping("/createTaskReport")
    public TaskReportDTO createNewTaskReport(@RequestBody TaskReportDTO taskReportDTO) {
        return taskReportService.createNewTaskReport(taskReportDTO);
    }

    @PutMapping("/updateTaskReport/{id}")
    public TaskReportDTO updateTaskReport(@PathVariable(value = "id") int taskReportId, @RequestBody TaskReportDTO taskReportDTO) {
        return taskReportService.updateTaskReport(taskReportId, taskReportDTO);
    }

    @GetMapping("/taskReport/{id}")
    public TaskReportDTO getTaskReportById(@PathVariable(value = "id") int taskReportId){
        return taskReportService.getTaskReportById(taskReportId);
    }
    @GetMapping("/taskReports")
    public java.util.List<TaskReportDTO> getAllTaskReports() {
        return taskReportService.getAllTaskReport();
    }

    @GetMapping (value = {"taskReports/search","taskReports/search/{query}"})
    public java.util.List<TaskReportDTO> searchTaskReport(@PathVariable(value = "query") Optional<String> query){
        return taskReportService.searchTaskReport(query);
    }
}
