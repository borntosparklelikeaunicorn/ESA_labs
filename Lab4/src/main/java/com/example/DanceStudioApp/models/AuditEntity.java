package com.example.DanceStudioApp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit")
public class AuditEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "event")
    @Enumerated(value = EnumType.STRING)
    private AuditEvent event;

    @Column(name = "table_name")
    private String table;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @Column(name = "info")
    private String info;

}
