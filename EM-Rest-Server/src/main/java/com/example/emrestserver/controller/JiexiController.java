package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.TestDomain;
import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api/em")
public class JiexiController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/jiexi")
    public ResponseEntity<Object> register(@RequestBody TestDomain testDomain) {
        if (testDomain == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {


//            try{
            //update database
            Person p = registerService.convertBasicInfoToPerson(testDomain.getBasicInfoDomain(), testDomain.getContactInfoDomain());
            System.out.println(testDomain.getAddressList());
            Address[] addressList = testDomain.getAddressList();
            for(int i = 0; i < addressList.length;i++){
                System.out.println(addressList[i]);
//                System.out.println(addressList.get(i).getAddressLine1());
//                System.out.println(addressList.get(i).getAddressLine2());
//                System.out.println(addressList.get(i).getAddressLine1());
            }
//            }catch (Exception e){
//                //can add more exceptions
//                return ResponseEntity.internalServerError().build();
//            }
            System.out.println(p);
            return ResponseEntity.ok().body(p);
        }

    }
}
