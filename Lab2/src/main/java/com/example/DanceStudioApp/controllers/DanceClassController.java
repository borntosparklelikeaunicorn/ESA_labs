package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.models.DanceClass;
import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.service.DanceClassService;
import com.example.DanceStudioApp.service.DanceStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DanceClassController {
    private final DanceClassService classService;
    private final DanceStudioService studioService;

    @Autowired
    public DanceClassController(DanceClassService classService, DanceStudioService studioService) {
        this.classService = classService;
        this.studioService = studioService;
    }

    @GetMapping("/classes")
    public String getAllClasses(Model model) {
        List<DanceClass> allClasses = classService.findAll();
        model.addAttribute("allClasses", allClasses);
        return "danceClassesPage";
    }

    @PostMapping("/add-class")
    public String addDanceClass(@ModelAttribute DanceClass newClass, @RequestParam("studio_id") Long studioId, Model model) {
        DanceStudio studio = studioService.findById(studioId);
        newClass.setStudio(studio);
        classService.saveDanceClass(newClass);
        return getAllClasses(model);
    }
}
