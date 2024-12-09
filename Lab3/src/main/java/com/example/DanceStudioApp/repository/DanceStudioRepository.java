package com.example.DanceStudioApp.repository;

import com.example.DanceStudioApp.models.DanceStudio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanceStudioRepository extends JpaRepository<DanceStudio, Long> {
}
