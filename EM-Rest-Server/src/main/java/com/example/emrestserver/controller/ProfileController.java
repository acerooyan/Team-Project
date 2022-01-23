package com.example.emrestserver.controller;

import com.example.emrestserver.constant.JwtConstant;
import com.example.emrestserver.domains.profile.*;
import com.example.emrestserver.domains.standalone.AddressDomain;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.security.util.CookieUtil;
import com.example.emrestserver.security.util.JwtUtil;
import com.example.emrestserver.service.AwsService;
import com.example.emrestserver.service.EmployeeService;
import com.example.emrestserver.service.ProfileUpdateService;
import com.example.emrestserver.service.ProfileUpdateService2;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/jwt")
public class ProfileController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProfileUpdateService profileUpdateService;

    @Autowired
    private ProfileUpdateService2 profileUpdateService2;

    @Autowired
    private AwsService awsService;

    @GetMapping("/profile")
    public ResponseEntity<ProfileDomain> getProfile(ServletRequest servletRequest) {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);

        try{
            //get application list from database
        //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
            ProfileDomain profileDomain = employeeService.getDataReady(email);
            return  ResponseEntity.ok().body(profileDomain);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/hr/profile/byemail")
    public ResponseEntity<ProfileDomain> getProfilebyemail(ServletRequest servletRequest,@RequestPart(value = "model") String model ) {

        String email = model;
        try{
            //get application list from database
            //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
            ProfileDomain profileDomain = employeeService.getDataReady(email);
            return  ResponseEntity.ok().body(profileDomain);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/em/profile/personalInfo")
    public ResponseEntity<Object> personalInfo(@RequestPart(value = "model") String model,ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);


        if ( model == null || email == null) {
            System.out.println("not found");
            return ResponseEntity.status(666).build();
        } else {
            try{
                Gson g = new Gson();
                PersonalInfoDomain personalInfoDomain = g.fromJson(model,PersonalInfoDomain.class);

                //todo: update DB with received personalInfo domain
                Person personUpdated =  profileUpdateService.buildPerson1(personalInfoDomain,email);
                System.out.println(personUpdated);

                profileUpdateService.updatePersonWithPerson(personUpdated);

                return ResponseEntity.ok().build();
            }catch (Exception e){
                System.out.println("error catch");
                return ResponseEntity.internalServerError().build();
            }
        }


    }

    @PutMapping("/em/profile/address")
    public ResponseEntity<Object> address(@RequestPart(value = "model") String model,ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);


        if ( model == null || email == null) {
            System.out.println("not found");
            return ResponseEntity.status(666).build();
        } else {
            try{
                Gson g = new Gson();

                //warning check correctness of following line
                AddressDomain[] addressDomains = g.fromJson(model,AddressDomain[].class);
                //todo: update DB with received personalInfo domain
                profileUpdateService2.changeAddress(addressDomains,email);
                return ResponseEntity.ok().build();
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PutMapping("/em/profile/contactInfo")
    public ResponseEntity<Object> contactInfo(@RequestPart(value = "model") String model,ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);


        if ( model == null || email == null) {
            System.out.println("not found");
            return ResponseEntity.status(666).build();
        } else {
            try{
                Gson g = new Gson();
                ContactInfoDomain contactInfoDomain = g.fromJson(model,ContactInfoDomain.class);
                //todo: update DB with received contactInfo domain
                Person personUpdated =  profileUpdateService.buildPerson2(contactInfoDomain,email);
                System.out.println(personUpdated);

                profileUpdateService.updatePersonWithPerson(personUpdated);


                return ResponseEntity.ok().build();
            }catch (Exception e){
                System.out.println("error catch");
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PutMapping("/em/profile/employment")
    public ResponseEntity<Object> employment(@RequestPart(value = "model") String model,ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);


        if ( model == null || email == null) {
            System.out.println("not found");
            return ResponseEntity.status(666).build();
        } else {
            try{
                Gson g = new Gson();
                EmploymentDomain employmentDomain = g.fromJson(model,EmploymentDomain.class);

                //todo: update DB with received EmploymentDomain
                profileUpdateService2.changeEmployee(employmentDomain, email);
                return ResponseEntity.ok().build();
            }catch (Exception e){
                System.out.println("error catch");
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PutMapping("/em/profile/emergencyContact")
    public ResponseEntity<Object> emergencyContact(@RequestPart(value = "model") String model,ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);


        if ( model == null || email == null) {
            System.out.println("not found");
            return ResponseEntity.status(666).build();
        } else {
            try{
                Gson g = new Gson();
                EmergencyContactDomain emergencyContactDomain = g.fromJson(model,EmergencyContactDomain.class);
                profileUpdateService2.changeEmergencyContact(emergencyContactDomain,email);
                //todo: update DB with received EmergencyContactDomain

                return ResponseEntity.ok().build();
            }catch (Exception e){
                System.out.println("error catch");
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PutMapping("/em/profile/avatar")
    public ResponseEntity<Object> avatar(@RequestPart("file") MultipartFile file, ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);


        String fileName;
        if ( file == null || email == null) {
            System.out.println("not found");
            return ResponseEntity.status(666).build();
        } else {
            try{


                fileName = awsService.uploadFile(file);
                Employee employee = employeeService.getEmpolyeeByEmail(email);
                employee.setAvatar(fileName);
                employeeService.updateEmployee(employee);
//                Gson g = new Gson();
//                EmergencyContactDomain emergencyContactDomain = g.fromJson(model,EmergencyContactDomain.class);
//                profileUpdateService2.changeEmergencyContact(emergencyContactDomain,email);

                return ResponseEntity.ok().build();
            }catch (Exception e){
                System.out.println("error catch");
                return ResponseEntity.internalServerError().build();
            }
        }
    }



}
