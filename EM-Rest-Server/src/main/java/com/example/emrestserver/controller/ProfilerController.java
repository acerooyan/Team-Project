package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.HrHomeDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/jwt")
public class ProfilerController {

//    @GetMapping("/hr/profiler")
//    public ResponseEntity<List<HrProfilerDomain>> hrProfiler() {
//
//        try{
//            //get application list from database
//        //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
//        return ResponseEntity.ok().body(hrHomeDomainList);
//        }catch (Exception e){
//            System.out.println("error catch");
//            return ResponseEntity.internalServerError().build();
//        }
//
//    }



}
