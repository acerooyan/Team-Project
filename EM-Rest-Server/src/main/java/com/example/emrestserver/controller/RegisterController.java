package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.MergeDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api/employee")
public class RegisterController {
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody MergeDomain mergeDomain) {
        if (mergeDomain == null) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            try{
                //update database
            }catch (Exception e){
                //can add more exceptions
                return ResponseEntity.internalServerError().build();
            }
            System.out.println(mergeDomain);
            return ResponseEntity.ok().build();
        }
    }

}
