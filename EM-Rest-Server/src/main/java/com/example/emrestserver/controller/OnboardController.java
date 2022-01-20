package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.MergeDomain;
import com.example.emrestserver.domains.combined.TestDomain;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api/em")
public class OnboardController {
    @Autowired
    RegisterService registerService;
    /*

     */
    @PostMapping("/register/test")
    public ResponseEntity<Object> register(@RequestBody TestDomain testDomain) {
        if (testDomain == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            System.out.println(testDomain.getBasicInfoDomain().getFirstName());
            System.out.println(testDomain.getBasicInfoDomain().getLastName());
            System.out.println(testDomain.getBasicInfoDomain().getMiddleName());
            System.out.println(testDomain.getBasicInfoDomain().getSsn());
            System.out.println(testDomain.getBasicInfoDomain().getDob());
            System.out.println(testDomain.getBasicInfoDomain().getGender());
            System.out.println(testDomain.getContactInfoDomain().getEmail());
            Person p = registerService.convertBasicInfoToPerson(testDomain.getBasicInfoDomain(), testDomain.getContactInfoDomain());
//            try{
                //update database
//            }catch (Exception e){
//                //can add more exceptions
//                return ResponseEntity.internalServerError().build();
//            }
            System.out.println(p);
            return ResponseEntity.ok().body(p);
        }

    }
}
