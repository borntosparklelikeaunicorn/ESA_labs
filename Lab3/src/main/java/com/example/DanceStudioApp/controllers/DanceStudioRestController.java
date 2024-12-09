package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.requests.DanceStudioCreatReq;
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
public class DanceStudioRestController {

    private final DanceStudioService studioService;

    // Получение списка всех студий
    @RequestMapping(value = "/api/studios", method = RequestMethod.GET)
    public ResponseEntity<?> getAllStudios(@RequestHeader("Accept") String acceptHeader) {
        List<DanceStudio> studios = studioService.findAll();
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            // Преобразование в XSLT
            String result = transformToXSLT(studios, "danceStudios.xslt");
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok(studios);
        }
    }

    // Получение информации о студии по id
    @RequestMapping(value = "/api/studios/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudioById(@PathVariable("id") Long id, @RequestHeader("Accept") String acceptHeader) {
        DanceStudio studio = studioService.findById(id);
        if (studio == null) {
            System.out.println("Studio with ID " + id + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Studio not found.");
        }
        System.out.println("Found Studio: " + studio);
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            // Преобразование в XSLT для конкретной студии
            String result = transformToXSLT(studio, "studioDetails.xslt");
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok(studio);
        }
    }

    // Создание новой студии
    @RequestMapping(value = "/api/studios/create", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createDanceStudio(@RequestBody DanceStudioCreatReq studioReq) {
        DanceStudio newStudio = new DanceStudio();
        newStudio.setName(studioReq.getName());
        newStudio.setAddress(studioReq.getAddress());
        newStudio.setPhone(studioReq.getPhone());
        studioService.saveDanceStudio(newStudio);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/api/studios")
                .build();
    }

    // Удаление студии
    @RequestMapping(value = "/api/studios/delete/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteStudio(@PathVariable("id") Long id) {
        try {
            studioService.deleteDanceStudio(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/api/studios")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
