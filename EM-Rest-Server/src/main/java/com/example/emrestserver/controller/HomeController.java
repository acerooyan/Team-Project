package com.example.emrestserver.controller;

import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class HomeController {

    @GetMapping("/hr/home")
    public ResponseEntity<ApplicationWorkFlow> hrHome() {
        //hard coded data
        ApplicationWorkFlow applicationWorkFlow = new ApplicationWorkFlow(null,null,new Date(),new Date(),"pending","lol","opt");

        try{
            //get application list from database
            
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(applicationWorkFlow);
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
