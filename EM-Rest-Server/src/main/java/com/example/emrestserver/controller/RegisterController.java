package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.MergeDomain;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.PersonalDocument;
import com.example.emrestserver.service.AwsService;
import com.example.emrestserver.service.PersonalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api/em")
public class RegisterController {

    @Autowired
    private AwsService awsService;

    @Autowired
    private PersonalDocumentService personalDocumentService;

    @PostMapping("/register")
    public ResponseEntity<PersonalDocument> register(@RequestPart("file") MultipartFile file) {
        PersonalDocument personalDocument;
        if (file == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            try{
                //update database
                String fileName = awsService.uploadFile(file);
                personalDocument =  personalDocumentService.buildDocument(fileName,personalDocumentService.getFirstEmployee());
            }catch (Exception e){
                //can add more exceptions
                return ResponseEntity.internalServerError().build();
            }

            return ResponseEntity.ok().body(personalDocument);
        }
    }

}
