package com.example.DanceStudioApp.controllers;

import com.example.DanceStudioApp.models.AuditEvent;
import com.example.DanceStudioApp.models.DanceStudio;
import com.example.DanceStudioApp.requests.DanceStudioCreateReq;
import com.example.DanceStudioApp.service.DanceStudioService;
import com.example.DanceStudioApp.service.DanceClassService;
import com.example.DanceStudioApp.utils.EventLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.DanceStudioApp.controllers.XSLTTransformer.transform2XLT;

@RestController
@RequiredArgsConstructor
public class DanceStudioRestController {
    private final DanceClassService classService;
    private final DanceStudioService studioService;
    private final EventLogger eventLogger;

    @RequestMapping(value = "/studios", method = RequestMethod.GET)
    public ResponseEntity<?> home(@RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(studioService.findAll(), "studioDetails.xslt")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    studioService.findAll()
            );
        }
    }

    @RequestMapping(value = "/studios/delete/{id}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> deleteStudio(@PathVariable("id") Long id) {
        try{
            studioService.deleteDanceStudio(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/studios")
                    .build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @RequestMapping(value = "/studios/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> selectedStudio(@PathVariable("id") long id, @RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(studioService.findAll(), "studioDetails.xslt",id)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    transform2XLT(studioService.findAll(), "studioDetails.xslt",id)
            );
        }
    }

    @RequestMapping(value = "/studios/create", method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createDanceStudio(@RequestParam("name") String Name,
                                               @RequestParam("address") String Address,
                                               @RequestParam("phone") String Phone) {
        try {
            DanceStudio newStudio = new DanceStudio();

            newStudio.setName(Name);
            newStudio.setAddress(Address);
            newStudio.setPhone(Phone);
            studioService.saveDanceStudio(newStudio);
            eventLogger.log(newStudio, AuditEvent.CREATE);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/studios")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}