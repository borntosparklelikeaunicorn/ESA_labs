package com.example.DanceStudioApp.service;

import com.example.DanceStudioApp.models.AuditEvent;
import com.example.DanceStudioApp.models.DanceClass;
import com.example.DanceStudioApp.repository.DanceClassRepository;
import com.example.DanceStudioApp.requests.DanceClassCreateReq;
import com.example.DanceStudioApp.utils.EventLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DanceClassService {
    private final DanceClassRepository classRepository;
    private final EventLogger eventLogger;


    public List<DanceClass> finAll(){
        return classRepository.findAll();
    }
    public DanceClass saveDanceClass(DanceClass danceClass) {;
        return classRepository.save(danceClass);
    }


    public void saveDanceClass(DanceClassCreateReq newDanceClassReq){
        DanceClass newDanceClass = new DanceClass();
        newDanceClass.setLevel(newDanceClassReq.getLevel());
        newDanceClass.setSchedule(newDanceClassReq.getSchedule());
        newDanceClass.setStyle(newDanceClassReq.getStyle());
        classRepository.save(newDanceClass);
    }
    public void deleteDanceClass(long id){
        eventLogger.log(classRepository.findById(id), AuditEvent.DELETE);
        classRepository.deleteById(id);
    }

}