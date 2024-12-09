package com.example.DanceStudioApp.service;

import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.repository.DanceStudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanceStudioService {
    private final DanceStudioRepository studioRepository;

    @Autowired
    public DanceStudioService(DanceStudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public List<DanceStudio> findAll() {
        List<DanceStudio> studios = studioRepository.findAll();
        System.out.println("Studios found: " + studios);
        return studioRepository.findAll();
    }

    public DanceStudio findById(Long id) {
        Optional<DanceStudio> studio = studioRepository.findById(id);
        return studio.orElse(null);
    }

    public void saveDanceStudio(DanceStudio studio) {
        studioRepository.save(studio);
    }

    // Добавляем метод для удаления
    public void deleteDanceStudio(Long id) {
        studioRepository.deleteById(id);
    }
}
