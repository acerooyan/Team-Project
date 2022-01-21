package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.HrHomeDomain;
import com.example.emrestserver.entity.Mail;
import com.example.emrestserver.service.HrHomeService;
import com.example.emrestserver.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/jwt")
public class HomeController {

    @Autowired
    HrHomeService hrHomeService;

    @Autowired
    EmailService emailService;

    @GetMapping("/hr/home")
    public ResponseEntity<List<HrHomeDomain>> hrHome() {

        try {
//            get application list from database
            List<HrHomeDomain> hrHomeDomainList = hrHomeService.mapDocumentWithEmployee();
            return ResponseEntity.ok().body(hrHomeDomainList);
        } catch (Exception e) {
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/sendNotification")
    public ResponseEntity<String> sendNotification(@RequestParam("email") String email){
        Mail mail = new Mail();
        mail.setMailTo(email);
        mail.setContentType("text/html");
        mail.setMailSubject("Document Notification");
        mail.setMailContent("Your documents are going to expire, please submit it ASAP.");
        emailService.sendEmail(mail);
        return ResponseEntity.ok().build();
    }
}

