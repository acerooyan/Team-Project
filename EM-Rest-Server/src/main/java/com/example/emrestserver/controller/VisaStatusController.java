package com.example.emrestserver.controller;

import com.example.emrestserver.domains.combined.EmployeeStatusDomain;
import com.example.emrestserver.domains.combined.HrVisaStatusDomain;
import com.example.emrestserver.domains.profile.ProfileDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@RestController
@RequestMapping("/jwt")
public class VisaStatusController {

    @GetMapping("/hr/visaStatus")
    public ResponseEntity<HrVisaStatusDomain> getAllVisaStatus() {
        HrVisaStatusDomain hrVisaStatusDomain = null;
        try{
            //todo: return domain object and put int in body.

            return  ResponseEntity.ok().body(hrVisaStatusDomain);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/hr/visaStatus")
    public ResponseEntity<HrVisaStatusDomain> updateVista(@RequestPart(value = "model") String model) {
        HrVisaStatusDomain hrVisaStatusDomain = null;
        try{
            //todo: update database visa status with domain obj

            return  ResponseEntity.ok().body(hrVisaStatusDomain);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/hr/visaStatus")
    public ResponseEntity<HrVisaStatusDomain> updateWorkFlow(@RequestPart(value = "model") String model) {
        HrVisaStatusDomain hrVisaStatusDomain = null;
        try{
            //todo: update database workflow with domain obj

            return  ResponseEntity.ok().body(hrVisaStatusDomain);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/em/visaStatus")
    public ResponseEntity<EmployeeStatusDomain> getWorkFlow(@RequestPart(value = "model") String model) {
        EmployeeStatusDomain employeeStatusDomain = null;
        try{
            //todo: return work flow

            return  ResponseEntity.ok().body(employeeStatusDomain);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/em/visaStatus")
    public ResponseEntity<EmployeeStatusDomain> updateWorkFlowAndFile(@RequestPart(value = "model") String model) {
        EmployeeStatusDomain employeeStatusDomain = null;
        try{
            //todo: update database workflow and document

            return  ResponseEntity.ok().body(employeeStatusDomain);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }
}
