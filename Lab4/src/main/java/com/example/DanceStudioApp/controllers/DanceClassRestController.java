package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.models.AuditEvent;
import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.models.DanceClass;
import com.example.DanceStudioApp.requests.DanceClassCreateReq;
import com.example.DanceStudioApp.service.DanceClassService;
import com.example.DanceStudioApp.service.DanceStudioService;
import com.example.DanceStudioApp.utils.EventLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.DanceStudioApp.controllers.XSLTTransformer.transform2XLT;
import static com.example.DanceStudioApp.controllers.XSLTTransformer.transform2XLT_class;

@RestController
@RequiredArgsConstructor
public class DanceClassRestController {
    private final DanceClassService classService;
    private final DanceStudioService studioService;
    private final EventLogger eventLogger;

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public ResponseEntity<?> classList(@RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(classService.finAll(), "classDetails.xslt")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    classService.finAll()
            );
        }
    }
    @RequestMapping(value = "/classes/create", method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createDanceClass(@RequestParam("style") String Style,
                                         @RequestParam("level") String Level,
                                         @RequestParam("schedule") String Schedule,
                                         @RequestParam("studio_id") long studio_id) {
        try {
            DanceClass newClass = new DanceClass();

            newClass.setStyle(Style);
            newClass.setLevel(Level);
            newClass.setSchedule(Schedule);
            newClass.setStudio_id(studio_id);
            classService.saveDanceClass(newClass);
            eventLogger.log(newClass, AuditEvent.CREATE);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/classes")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/classes/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> selectedDanceClass(@PathVariable("id") long id, @RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT_class(classService.finAll(), "classDetails.xslt",id)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT_class(classService.finAll(), "classDetails.xslt",id)
            );
        }
    }


    @RequestMapping(value = "/classes/delete/{id}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> deleteClass(@PathVariable("id") Long id) {
        try{
            classService.deleteDanceClass(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/classes")
                    .build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}