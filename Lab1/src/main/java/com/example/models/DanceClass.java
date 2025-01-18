package com.example.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "danceclass")
public class DanceClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "style")
    private String name;

    @Column(name = "level")
    private String level;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "studio_id")
    private int studioId;
}
