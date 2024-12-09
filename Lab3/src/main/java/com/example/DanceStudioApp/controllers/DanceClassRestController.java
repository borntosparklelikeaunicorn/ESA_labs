package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.models.DanceClass;
import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.requests.DanceClassCreatReq;
import com.example.DanceStudioApp.service.DanceClassService;
import com.example.DanceStudioApp.service.DanceStudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.DanceStudioApp.controllers.XSLTTransformer.transformToXSLT;

@RestController
@RequiredArgsConstructor
public class DanceClassRestController {

    private final DanceClassService classService;
    private final DanceStudioService studioService;

    @RequestMapping(value = "/api/classes", method = RequestMethod.GET)
    public ResponseEntity<?> getAllClasses(@RequestHeader("Accept") String acceptHeader) {
        List<DanceClass> classes = classService.findAll();
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            // Преобразование в XSLT
            String result = transformToXSLT(classes, "danceClasses.xslt");
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok(classes);
        }
    }

    @RequestMapping(value = "/api/classes/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getClassById(@PathVariable("id") Long id, @RequestHeader("Accept") String acceptHeader) {
        DanceClass danceClass = classService.findById(id);
        if (danceClass == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found.");
        }

        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            String result = transformToXSLT(danceClass, "classDetails.xslt");
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok(danceClass);
        }
    }

    @RequestMapping(value = "/api/classes/create", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createDanceClass(@RequestBody DanceClassCreatReq classReq) {
        DanceStudio studio = studioService.findById(classReq.getStudioId());
        if (studio == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Studio not found.");
        }

        DanceClass newClass = new DanceClass();
        newClass.setStyle(classReq.getStyle());
        newClass.setLevel(classReq.getLevel());
        newClass.setSchedule(classReq.getSchedule());
        newClass.setStudio(studio);
        classService.saveDanceClass(newClass);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/api/classes")
                .build();
    }

    @RequestMapping(value = "/api/classes/delete/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteClass(@PathVariable("id") Long id) {
        try {
            classService.deleteDanceClass(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/api/classes")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
