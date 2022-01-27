package com.example.emrestserver.controller;


import com.example.emrestserver.constant.JwtConstant;
import com.example.emrestserver.domains.TestDomain;
import com.example.emrestserver.domains.profile.PersonalInfoDomain;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.security.util.CookieUtil;
import com.example.emrestserver.security.util.JwtUtil;
import com.example.emrestserver.service.ProfileUpdateService;
import com.example.emrestserver.service.UtilService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @Autowired
    private UtilService utilService;

    @Autowired
    private ProfileUpdateService profileUpdateService;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/test")
    public String test1(){
        return "test";
    }

    @PostMapping("/test2")
    public ResponseEntity<Object> test2(@RequestBody Person person){
        if(person == null || !person.getFirstname().equals("James")){
            return ResponseEntity.notFound().build();
        } else {
//            System.out.println(person);
            return ResponseEntity.ok().body(person);
        }
    }

    @PostMapping("/test3")
    public ResponseEntity<TestDomain> test2(@RequestBody TestDomain testDomain){
        if(testDomain == null ){
            return ResponseEntity.notFound().build();
        } else {

//            System.out.println(testDomain.getPerson());
            return ResponseEntity.ok().body(testDomain);
        }
    }

    @GetMapping("/test4")
    public ResponseEntity<Employee> test4(ServletRequest servletRequest){

//        System.out.println(utilService.getEmployeeByEmail("jamesh970327@gmail.com"));
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);
//        System.out.println(email);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/test5")
    public ResponseEntity<Employee> test5(@RequestPart(value = "model") String model,ServletRequest servletRequest){

        String email = "jamesh970327@gmail.com";

        if ( model == null || email == null) {
            logger.error("email/model not found");
//            System.out.println("not found");
            return ResponseEntity.status(666).build();
        } else {
            try{
                Gson g = new Gson();
                PersonalInfoDomain personalInfoDomain = g.fromJson(model,PersonalInfoDomain.class);
                System.out.println(personalInfoDomain);
                //todo: update DB with received personalInfo domain
                Person personUpdated =  profileUpdateService.buildPerson1(personalInfoDomain,email);
//                System.out.println(personUpdated);
                profileUpdateService.updatePersonWithPerson(personUpdated);




                return ResponseEntity.ok().build();
            }catch (Exception e){
//                System.out.println("error catch");
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
    }


    }




}
