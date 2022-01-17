package com.example.emrestserver.controller;

import com.example.emrestserver.entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/em")
public class OnboardController {


    @PostMapping("/af")
    public String test2(@RequestBody Person person) {
        //update database

        return "redirect:http://localhost:4200";
    }
}
