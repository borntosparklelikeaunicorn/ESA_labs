package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.service.DanceClassService;
import com.example.DanceStudioApp.service.DanceStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DanceClassController {
    private final DanceClassService classService;
    private final DanceStudioService studioService;

    @Autowired
    public DanceClassController(DanceClassService classService,DanceStudioService studioService) {
        this.classService = classService;
        this.studioService = studioService;
    }

}