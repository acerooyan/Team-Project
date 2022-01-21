package com.example.emrestserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<Object> getStatus() {

        try{
            String status = null;
            //todo: get status from applicationworkflow
            if(status != null && status.equals("complete")){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.status(666).build();
            }

        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }
}
