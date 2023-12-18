package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestVolunteerDTO {
    private int id;
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^0\\d{9}$",message = "phải nhập đúng định dạng sđt 0xxxxxxxxx")
    private String phone;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Timestamp date_of_birth;
    private String address;
    private String reason;
    private int campain_id;
    private String campain_name;
    private boolean status;


}
