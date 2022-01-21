package com.example.emrestserver.controller;

import com.example.emrestserver.domains.profile.PersonalInfoDomain;
import com.example.emrestserver.domains.profile.ProfileDomain;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/jwt")
public class ProfileController {

    @GetMapping("/profile")
    public ResponseEntity<ProfileDomain> getProfile() {

        try{
            //get application list from database
        //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
        return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/em/profile/personalInfo/{email}")
    public ResponseEntity<Object> personalInfo(@RequestPart(value = "model") String model,@PathVariable String email) {



        if ( model == null || email == null) {
            System.out.println("not found");
            return ResponseEntity.unprocessableEntity().build();
        } else {
            try{
                Gson g = new Gson();
                PersonalInfoDomain personalInfoDomain = g.fromJson(model,PersonalInfoDomain.class);
                //call update service
                //get application list from database
                //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
                return ResponseEntity.ok().build();
            }catch (Exception e){
                System.out.println("error catch");
                return ResponseEntity.internalServerError().build();
            }
        }


    }

    @PutMapping("/em/profile/address")
    public ResponseEntity<Object> address() {

        try{
            //get application list from database
            //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/em/profile/contactInfo")
    public ResponseEntity<Object> contactInfo() {

        try{
            //get application list from database
            //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/em/profile/employment")
    public ResponseEntity<Object> employment() {

        try{
            //get application list from database
            //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/em/profile/emergencyContact")
    public ResponseEntity<Object> emergencyContact() {

        try{
            //get application list from database
            //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }




}
