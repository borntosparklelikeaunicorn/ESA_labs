package com.example.service;

import com.example.models.DanceClass;
import com.example.models.DanceStudio;
import com.example.repository.DanceClassRepository;
import com.example.repository.DanceStudioRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class DanceStudioService {
    @EJB
    private DanceStudioRepository studioRepository;

    public List<DanceStudio> getAll() {
        return studioRepository.getAll();
    }
    public void save(DanceStudio newStudio) {
        studioRepository.save(newStudio);
    }
}