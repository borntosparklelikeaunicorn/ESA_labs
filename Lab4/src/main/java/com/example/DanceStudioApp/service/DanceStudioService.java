package com.example.DanceStudioApp.service;

import com.example.DanceStudioApp.models.AuditEvent;
import com.example.DanceStudioApp.models.DanceClass;
import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.repository.DanceStudioRepository;
import com.example.DanceStudioApp.requests.DanceStudioCreateReq;
import com.example.DanceStudioApp.utils.EventLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DanceStudioService {
    private final DanceStudioRepository studioRepository;
    private final EventLogger eventLogger;

    public List<DanceStudio> findAll(){
        return studioRepository.findAll();
    }

    public DanceStudio saveDanceStudio(DanceStudio studio){
        return studioRepository.save(studio);
    }

    public DanceStudio findById(Long id) {
        return studioRepository.getOne(id);
    }

    public void deleteDanceStudio(Long id){
        eventLogger.log(studioRepository.findById(id), AuditEvent.DELETE);
        studioRepository.deleteById(id);
    }
    public void saveDanceStudio(DanceStudioCreateReq newStudio) {
        DanceStudio studio = new DanceStudio();
        studio.setName(newStudio.getName());
        studio.setAddress(newStudio.getAddress());
        studio.setPhone(newStudio.getPhone());
        studioRepository.save(studio);
    }
}