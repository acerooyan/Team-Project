package com.example.emrestserver.controller;

import com.example.emrestserver.domains.standalone.AddressDomain;
import com.example.emrestserver.domains.standalone.ContactInfoDomain;
import com.example.emrestserver.domains.standalone.BasicInfoDomain;
import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.RegisterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> register(@RequestPart("basicInfo") String basicInfo,
                                           @RequestPart("contactInfo") String contactInfo,
                                           @RequestPart("addressList") String addressList
    ) throws JsonProcessingException {



        Gson g = new Gson();
        BasicInfoDomain basicInfoDomain = g.fromJson(basicInfo, BasicInfoDomain.class);
        ContactInfoDomain contactInfoDomain = g.fromJson(contactInfo, ContactInfoDomain.class);
        System.out.println(contactInfoDomain.getAddressDaoList());
        ObjectMapper objectMapper = new ObjectMapper();


        AddressDomain[] addressDomains = objectMapper.readValue(addressList, AddressDomain[].class);
        System.out.println(Arrays.toString(addressDomains));


        if (basicInfo == null) {
            System.out.println("basicInfo not found");
            return ResponseEntity.unprocessableEntity().build();
        } else {
            Person p = registerService.convertBasicInfoToPerson(basicInfoDomain, contactInfoDomain);
            // add addresses
            registerService.addAddress(p, addressDomains);
//            try{
            //update database
//            }catch (Exception e){
//                //can add more exceptions
//                return ResponseEntity.internalServerError().build();
//            }
            //System.out.println(p);
            return ResponseEntity.ok().body(basicInfoDomain);
        }

    }
}
