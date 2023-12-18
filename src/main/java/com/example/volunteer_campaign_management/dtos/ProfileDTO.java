package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDTO {
    private String firstname;
    private String lastname;
    private String avatar;
    private String address;
    private String email;
    private String phone;
    private String rol;
    private String department;
}
