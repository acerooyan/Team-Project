package com.example.emrestserver.controller;


import com.example.emrestserver.domains.combined.TestDomain;
import com.example.emrestserver.domains.standalone.*;
import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.entity.VisaStatus;
import com.example.emrestserver.service.RegisterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api/em")
public class OnboardController {
    @Autowired
    RegisterService registerService;

    /*

     */
    @PostMapping("/register/test")
    public ResponseEntity<Object> register(@RequestPart("testDomain") String testDomainString
//                                           @RequestPart("contactInfo") String contactInfo
//                                           @RequestPart("addressList") String addressList
    ) throws JsonProcessingException {




//        BasicInfoDomain basicInfoDomain = g.fromJson(basicInfo,BasicInfoDomain.class);
//        ContactInfoDomain contactInfoDomain = g.fromJson(contactInfo,ContactInfoDomain.class);


        if (testDomainString == null) {
            System.out.println("basicInfo not found");
            return ResponseEntity.unprocessableEntity().build();
        } else {
//            try{
            //update database
            Gson g = new Gson();
            //拆分成小的domain
            TestDomain testDomain = g.fromJson(testDomainString, TestDomain.class);
            BasicInfoDomain basicInfoDomain = testDomain.getBasicInfoDomain();
            ContactInfoDomain contactInfoDomain = testDomain.getContactInfoDomain();
            ContactReferenceDomain contactReferenceDomain = testDomain.getContactReferenceDomain();
            ContactEmergencyDomain contactEmergencyDomain = testDomain.getContactEmergencyDomain();
            ResidentialStatusDomain residentialStatusDomain = testDomain.getResidentialStatusDomain();
            System.out.println(basicInfoDomain);
            System.out.println(contactInfoDomain);
            System.out.println(contactReferenceDomain);
            System.out.println(contactEmergencyDomain);
            System.out.println(residentialStatusDomain);

            //call services
            //1. add person and return a person(with id)
            Person p = registerService.convertBasicInfoToPerson(basicInfoDomain, contactInfoDomain);

            // add addresses
            AddressDomain[] addressDomains = contactInfoDomain.getAddressDomains();
            registerService.addAddress(p, addressDomains);

            // todo: add visaStatus
            VisaStatus visaStatus = registerService.addVisaStatus(residentialStatusDomain);
            // todo: add employee
//            registerService.addEmployee()
            Employee employee;
            // todo : add person & contact
//            registerService.addContactReference(contactReferenceDomain);

            // todo: add personalDocument
            // add contact person
            // reference person included address



            return ResponseEntity.ok().body(basicInfoDomain);
//            }catch (Exception e){
//                //can add more exceptions
//                return ResponseEntity.internalServerError().build();
//            }
            //System.out.println(p);
        }

    }
}
