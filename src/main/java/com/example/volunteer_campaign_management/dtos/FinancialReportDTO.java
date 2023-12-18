package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialReportDTO {

    private int financialreport_id;
    @NotEmpty(message = "không đươc để trống")
    private String name;
    @NotEmpty(message = "không đươc để trống")
    @Pattern(regexp ="^\\d+$" , message = "phải nhập đúng định dạng số")
    private String amount;
    @NotEmpty(message = "không đươc để trống")
    @Pattern(regexp = "^\\d+$" ,message = "phải nhập đúng định dạng số")
    private String total_expenses;
    @NotEmpty(message = "không đươc để trống")
    private String description;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Timestamp created_at;
    @NotEmpty(message = "không đươc để trống")
    private String note;
    private int campaign_id;
    private int user_id;
    private String campaign_name;
    private String user_name;

}