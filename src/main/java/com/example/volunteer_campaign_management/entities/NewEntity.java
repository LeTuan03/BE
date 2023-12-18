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
@Table(name="news")
public class NewEntity {
    @Id
    @Column(name = "new_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newId;
    private String title;
    private String content;
    @Column(name = "created_date")
    private Timestamp created_at;
    private String image;
}
