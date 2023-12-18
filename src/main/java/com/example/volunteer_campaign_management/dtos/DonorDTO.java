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
public class DonorDTO {
    private int donor_id;
    @NotEmpty(message = "không đươc để trống")
    private String name;
    @NotEmpty(message = "không đươc để trống")
    @Pattern(regexp = "^\\d+$" ,message = "phải nhập đúng định dạng số")
    private String amount;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Timestamp donate_date;
    @NotEmpty(message = "không đươc để trống")
    private String description;
    private int campaign_id;
    private String campaign_name;

}
