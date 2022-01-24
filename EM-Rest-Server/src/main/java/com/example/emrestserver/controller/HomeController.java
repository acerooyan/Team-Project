package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.HrHomeDomain;
import com.example.emrestserver.entity.Mail;
import com.example.emrestserver.service.AwsService;
import com.example.emrestserver.service.EmployeeService;
import com.example.emrestserver.service.HrHomeService;
import com.example.emrestserver.service.PersonalDocumentService;
import com.example.emrestserver.service.email.EmailService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/jwt")
public class HomeController {

    @Autowired
    HrHomeService hrHomeService;

    @Autowired
    EmailService emailService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PersonalDocumentService personalDocumentService;

    @Autowired
    private AwsService awsService;



    @GetMapping("/hr/home")
    public ResponseEntity<HrHomeDomain[]> hrHome() {

        try {
            HrHomeDomain[] hrHomeDomains = hrHomeService.mainService();
            return ResponseEntity.ok().body(hrHomeDomains);
        } catch (Exception e) {
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping("/hr/sendNotification")
    public ResponseEntity<String> sendNotification(@RequestPart("email") String email){
        Mail mail = new Mail();
        mail.setMailTo(email);
        mail.setContentType("text/html");
        mail.setMailSubject("Document Notification");
        mail.setMailContent("Your documents are going to expire, please submit it ASAP.");
        emailService.sendEmail(mail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hr/home/download")
    public ResponseEntity<String> hrHome(@RequestPart("email") String email) {

        try {
//            get application list from database
            String file = personalDocumentService.getPersonalDocumentByTitle("I-983", employeeService.getEmpolyeeByEmail(email).getId()).getPath();
            return ResponseEntity.ok().body(file);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/hr/home/upload")
    public ResponseEntity<Object> updateWorkFlowAndFile(@RequestPart(value = "file") MultipartFile file,@RequestPart String email) {
        try{
            //todo: update database workflow and document
            String fileName = awsService.uploadFile(file);

            personalDocumentService.updatePersonalDocument(fileName, employeeService.getEmpolyeeByEmail(email));


            return  ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }


}

