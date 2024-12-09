package com.example.DanceStudioApp.service;

import com.example.DanceStudioApp.models.DanceClass;
import com.example.DanceStudioApp.repository.DanceClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanceClassService {
    private final DanceClassRepository classRepository;

    @Autowired
    public DanceClassService(DanceClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<DanceClass> findAll() {
        return classRepository.findAll();
    }

    public DanceClass findById(Long id) {
        Optional<DanceClass> danceClass = classRepository.findById(id);
        return danceClass.orElse(null);
    }

    public void saveDanceClass(DanceClass danceClass) {
        classRepository.save(danceClass);
    }

    // Добавляем метод для удаления
    public void deleteDanceClass(Long id) {
        classRepository.deleteById(id);
    }
}
