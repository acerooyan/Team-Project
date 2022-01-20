package com.example.emrestserver.controller;


import com.example.emrestserver.domains.TestDomain;
import com.example.emrestserver.entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
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

    @PostMapping("/upload")
    public ResponseEntity<Object> test3(@RequestParam(value = "file",required = false)MultipartFile file,@RequestParam("model")String model){

        if(file == null ){
            System.out.println("file not found");
            return ResponseEntity.notFound().build();
        } else if(model == null){
            System.out.println("model not found");
            return ResponseEntity.notFound().build();
        }else{
            System.out.println(file);
            System.out.println(model);
            return ResponseEntity.ok().build();
        }

    }



}
