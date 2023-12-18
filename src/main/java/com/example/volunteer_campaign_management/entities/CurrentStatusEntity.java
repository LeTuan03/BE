package com.example.volunteer_campaign_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Current_Status")
public class CurrentStatusEntity {
    @Id
    @Column(name = "current_Status_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    private String name;
    private String description;

}
