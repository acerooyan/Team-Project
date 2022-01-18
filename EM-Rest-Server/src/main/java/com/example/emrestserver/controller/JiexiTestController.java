//package com.example.emrestserver.controller;
//
//import com.example.emrestserver.domain.MergeSmallDomain;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class JiexiTestController {
//    @PostMapping("/test3")
//    public ResponseEntity<Object> test2(@RequestBody MergeSmallDomain mergeSmallDomain){
//        if(mergeSmallDomain == null || !mergeSmallDomain.getRegistrationDomain().getUsername().equals("James")){
//            return ResponseEntity.notFound().build();
//        } else {
//            System.out.println(person);
//            return ResponseEntity.ok().body(person);
//        }
//    }
//}
