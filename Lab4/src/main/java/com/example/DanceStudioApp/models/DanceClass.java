package com.example.DanceStudioApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "danceclass")
public class DanceClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "style", nullable = false)
    private String style;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "studio_id")
    private long studio_id;


}