package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.HrHomeDomain;
import com.example.emrestserver.service.HrHomeService;
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

    @GetMapping("/employee/home")
    public String employeeHome() {
        try {
            //add new person in database
            return "success";
        } catch (Exception e) {
            return "error";
        }

    }
}
