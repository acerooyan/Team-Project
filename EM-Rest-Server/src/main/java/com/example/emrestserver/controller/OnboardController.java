package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.ContactInfoDomain;
import com.example.emrestserver.domains.combined.MergeDomain;
import com.example.emrestserver.domains.combined.TestDomain;
import com.example.emrestserver.domains.standalone.BasicInfoDomain;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.RegisterService;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                                           @RequestParam("contactInfo")String contactInfo) throws JSONException {

        //JSONObject jsonObject = new JSONObject(basicInfo);

        Gson g = new Gson();
        BasicInfoDomain basicInfoDomain = g.fromJson(basicInfo,BasicInfoDomain.class);

        ContactInfoDomain contactInfoDomain = g.fromJson(contactInfo,ContactInfoDomain.class);

        System.out.println(Arrays.toString(contactInfoDomain.getAddressDomains()));
        if (basicInfo == null) {
            System.out.println("basicInfo not found");
            return ResponseEntity.unprocessableEntity().build();
        } else {
//            System.out.println(testDomain.getBasicInfoDomain().getFirstName());
//            System.out.println(testDomain.getBasicInfoDomain().getLastName());
//            System.out.println(testDomain.getBasicInfoDomain().getMiddleName());
//            System.out.println(testDomain.getBasicInfoDomain().getSsn());
//            System.out.println(testDomain.getBasicInfoDomain().getDob());
//            System.out.println(testDomain.getBasicInfoDomain().getGender());
//            System.out.println(testDomain.getContactInfoDomain().getEmail());
            Person p = registerService.convertBasicInfoToPerson(basicInfoDomain, contactInfoDomain);
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
