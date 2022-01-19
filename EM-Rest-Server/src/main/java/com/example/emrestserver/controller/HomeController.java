package com.example.emrestserver.controller;

import com.example.emrestserver.domain.HrHomeDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.HrHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class HomeController {

    @Autowired
    HrHomeService hrHomeService;

    @GetMapping("/hr/home")
    public ResponseEntity<Employee > hrHome() {
        //hard coded data
        ApplicationWorkFlow applicationWorkFlow = new ApplicationWorkFlow(null,null,new Date(),new Date(),"pending","lol","opt");

//        try{
            //get application list from database

         List<HrHomeDomain> hrHomeDomainList = hrHomeService.mapDocumentWithEmployee();

//        }catch (Exception e){
//            System.out.println("error catch");
//            return ResponseEntity.internalServerError().build();
//        }
        return ResponseEntity.ok().body(hrHomeDomainList.get(0).getEmployee());
    }

    @GetMapping("/employee/home")
    public String  employeeHome() {
        try{
            //add new person in database
            return "success";
        }catch (Exception e){
            return "error";
        }

    }
}
