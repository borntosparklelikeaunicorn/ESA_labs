package com.example.service;

import com.example.models.DanceClass;
import com.example.repository.DanceClassRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class DanceClassService {
    @EJB
    private DanceClassRepository classRepository;
    public List<DanceClass> getAll() {
        return classRepository.getAll();
    }
    public void save(DanceClass newClass) {
        classRepository.save(newClass);
    }
}