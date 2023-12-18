package com.example.volunteer_campaign_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private int accountId;
    private String firstname;
    private String lastname;
    private String avatar;
    private String address;
    private String email;
    private String phone;
    private String password;
    private int roleId;
    private int departmentId;
    private String roleName;
    private Byte status;
    private String departmentName;
    private String currentPassword;
}
