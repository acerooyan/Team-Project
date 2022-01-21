package com.example.emrestserver.controller;


import com.example.emrestserver.domains.TestDomain;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    @Autowired
    private UtilService utilService;

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

    @GetMapping("/test4")
    public ResponseEntity<Employee> test4(){
        System.out.println(utilService.getEmployeeByEmail("jamesh970327@gmail.com"));;
        return ResponseEntity.ok().build();
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
