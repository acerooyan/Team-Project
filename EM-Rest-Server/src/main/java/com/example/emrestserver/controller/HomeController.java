package com.example.emrestserver.controller;

import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.HrHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class HomeController {

    @Autowired
    HrHomeService hrHomeService;

    @GetMapping("/hr/home")
    public ResponseEntity<ApplicationWorkFlow> hrHome() {
        //hard coded data
        ApplicationWorkFlow applicationWorkFlow = new ApplicationWorkFlow(null,null,new Date(),new Date(),"pending","lol","opt");

//        try{
            //get application list from database
            System.out.println("lanzhulema");
            System.out.println(hrHomeService.getFetchedAllEmployee().get(0).getId());
//        }catch (Exception e){
//            System.out.println("error catch");
//            return ResponseEntity.internalServerError().build();
//        }
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
