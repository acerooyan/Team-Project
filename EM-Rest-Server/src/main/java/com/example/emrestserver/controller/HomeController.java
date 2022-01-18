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

    @PostMapping("/employee/home")
    public ResponseEntity<Object> person(@RequestBody Person person) {
        if (person == null
                || person.getFirstname() == null
                || person.getLastname()== null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            try{
                //add new person in database
            }catch (Exception e){
                return ResponseEntity.internalServerError().build();
            }
            System.out.println(person);
            return ResponseEntity.ok().build();
        }
    }
}
