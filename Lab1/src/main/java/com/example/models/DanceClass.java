package com.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "danceclass")
public class DanceClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "style")
    private String name;

    @Column(name = "level")
    private String level;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "studio_id")
    private int studioId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    @Override
    public String toString() {
        return "DanceClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", schedule='" + schedule + '\'' +
                ", studioId=" + studioId +
                '}';
    }
}
