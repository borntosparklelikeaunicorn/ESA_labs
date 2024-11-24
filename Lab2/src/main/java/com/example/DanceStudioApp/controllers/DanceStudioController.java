package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.service.DanceStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DanceStudioController {
    private final DanceStudioService studioService;

    @Autowired
    public DanceStudioController(DanceStudioService studioService) {
        this.studioService = studioService;
    }

    @GetMapping("/studios")
    public String getAllStudios(Model model) {
        List<DanceStudio> allStudios = studioService.findAll();
        model.addAttribute("allStudios", allStudios);
        return "danceStudiosPage";
    }

    @PostMapping("/add-studio")
    public String addDanceStudio(@ModelAttribute DanceStudio newStudio, Model model) {
        studioService.saveDanceStudio(newStudio);
        return getAllStudios(model);
    }
}
