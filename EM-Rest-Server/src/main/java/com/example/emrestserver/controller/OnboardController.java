package com.example.emrestserver.controller;


import com.example.emrestserver.domains.combined.TestDomain;
import com.example.emrestserver.domains.standalone.*;
import com.example.emrestserver.entity.*;
import com.example.emrestserver.service.AwsService;
import com.example.emrestserver.service.PersonalDocumentService;
import com.example.emrestserver.service.RegisterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/")
public class OnboardController {
    @Autowired
    RegisterService registerService;

    @Autowired
    private AwsService awsService;

    @Autowired
    private PersonalDocumentService personalDocumentService;
    /*

     */
    @PostMapping("/register/test")
    public ResponseEntity<Object> register(@RequestPart(value = "model") String testDomainString,
                                           @RequestPart(value = "Avatar", required = false)MultipartFile file1,
                                           @RequestPart(value = "Driver", required = false)MultipartFile file2,
                                           @RequestPart(value = "Work", required = false)MultipartFile file3
//
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
            ContactEmergencyDomain contactEmergencyDomain = testDomain.getContactEmergencyDomain();
            ResidentialStatusDomain residentialStatusDomain = testDomain.getResidentialStatusDomain();
            CarInfoDomain carInfoDomain = testDomain.getCarInfoDomain();
            System.out.println(basicInfoDomain);
            System.out.println(contactInfoDomain);
            System.out.println(contactEmergencyDomain);
            System.out.println(residentialStatusDomain);
            System.out.println(carInfoDomain);

            //call services
            //1. add person and return a person(with id)
            Person p = registerService.convertBasicInfoToPerson(basicInfoDomain, contactInfoDomain);

            // add addresses
            AddressDomain[] addressDomains = contactInfoDomain.getAddressDomains();
            registerService.addAddress(p, addressDomains);

            // add visaStatus
            VisaStatus visaStatus = registerService.addVisaStatus(residentialStatusDomain);
            // todo: add employee

            //avatar:  get file name -> from aws service
            String fileName1;
            if (file1 != null){
                 fileName1 = awsService.uploadFile(file1);
            }else{
                fileName1 = "";
            }
            Employee employee = registerService.addEmployee(p,visaStatus, residentialStatusDomain, carInfoDomain, fileName1);
            System.out.println("From controller: ");

            System.out.println(employee);

            // add personalDocument
            PersonalDocument personalDocument =  personalDocumentService.buildDocument(fileName1,employee);


            // todo: add reference contact and emergency contact
            if (testDomain.getContactReferenceDomain() != null){
                ContactReferenceDomain contactReferenceDomain = testDomain.getContactReferenceDomain();
                registerService.addContactReference(contactReferenceDomain, employee);
            }
            registerService.addContactEmergency(contactEmergencyDomain, employee);



            // todo: add files
            if (file2 != null){
                String fileName2 = awsService.uploadFile(file2);
                personalDocument =  personalDocumentService.buildDocument(fileName2,employee);

            }
            if (file3 != null){
                String fileName3 = awsService.uploadFile(file3);
                personalDocument =  personalDocumentService.buildDocument(fileName3,employee);

            }
            registerService.addApplicationWorkFlow(employee);

            return ResponseEntity.ok().build();
//            }catch (Exception e){
//                //can add more exceptions
//                return ResponseEntity.internalServerError().build();
//            }
            //System.out.println(p);
        }

    }
}
