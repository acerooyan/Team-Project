package com.example.emrestserver.controller;

import com.example.emrestserver.entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class JiexiTempController {
    @PostMapping("/test")
    public ResponseEntity<Object> person(@RequestBody Person person) {
        if (person == null
                || person.getFirstname() == null
                || person.getLastname()== null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            try{
                //add new person in database
                // person -> basic & contactinfo
                //
            }catch (Exception e){
                return ResponseEntity.internalServerError().build();
            }
            System.out.println(person);
            return ResponseEntity.ok().build();
        }
    }
}
