package com.example.emrestserver.controller;

import com.example.emrestserver.domains.visaStatus.EmployeeStatusDomain;
import com.example.emrestserver.domains.visaStatus.HrVisaStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.service.EmployeeVisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class VisaStatusController {

    @Autowired
    EmployeeVisaService employeeVisaService;

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
    public ResponseEntity<EmployeeStatusDomain> getWorkFlow() {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
//        String email = JwtUtil.getSubjectFromJwt(token);
        String email = "jamesh970327@gmail.com";
        EmployeeStatusDomain employeeStatusDomain = null;
        try{
            //todo: return work flow
            ApplicationWorkFlow largestWorkFlow =  employeeVisaService.getLargestWorkFlowByEmail(email);


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
