package com.example.volunteer_campaign_management.jwts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private long userId;
    private String email;
    private String role;
    private Byte status;
}
