package com.example.DanceStudioApp.repository;

import com.example.DanceStudioApp.models.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditRepository extends JpaRepository<AuditEntity, UUID> {
}
