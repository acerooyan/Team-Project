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

    @GetMapping("/hr/download")
    public ResponseEntity<String> hrHome(@RequestParam("email") String email) {

        try {
//            get application list from database
            String file = personalDocumentService.getPersonalDocumentByTitle("I-983", employeeService.getEmpolyeeByEmail(email).getId()).getPath();
            return ResponseEntity.ok().body(file);
        } catch (Exception e) {
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/hr/upload")
    public ResponseEntity<Object> updateWorkFlowAndFile(@RequestPart(value = "file") MultipartFile file) {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
//        String email = JwtUtil.getSubjectFromJwt(token);
        String email = "jamesh970327@gmail.com";
        try{
            //todo: update database workflow and document
            Gson g = new Gson();
            String fileName = awsService.uploadFile(file);

            personalDocumentService.updatePersonalDocument(fileName, employeeService.getEmpolyeeByEmail(email));

            return  ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }


}

