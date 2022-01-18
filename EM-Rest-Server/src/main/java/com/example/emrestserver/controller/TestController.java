package com.example.emrestserver.controller;


import com.example.emrestserver.domain.TestDomain;
import com.example.emrestserver.entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test1(){
        return "test";
    }

    @PostMapping("/test2")
    public ResponseEntity<Object> test2(@RequestBody Person person){
        if(person == null || !person.getFirstname().equals("James")){
            return ResponseEntity.notFound().build();
        } else {
            System.out.println(person);
            return ResponseEntity.ok().body(person);
        }
    }

    @PostMapping("/test3")
    public ResponseEntity<TestDomain> test2(@RequestBody TestDomain testDomain){
        if(testDomain == null ){
            return ResponseEntity.notFound().build();
        } else {
            System.out.println(testDomain.getPerson());
            return ResponseEntity.ok().body(testDomain);
        }
    }



}
