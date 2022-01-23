package com.example.emrestserver.controller;

import com.example.emrestserver.domains.visaStatus.EmployeeStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class HrHireController {

    @PutMapping("/hr/hire")
    public ResponseEntity<EmployeeStatusDomain> getWorkFlow() {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
//        String email = JwtUtil.getSubjectFromJwt(token);
        String email = "jamesh970327@gmail.com";
        EmployeeStatusDomain employeeStatusDomain = null;
        try{
            //todo: return work flow
            //ApplicationWorkFlow largestWorkFlow =  employeeVisaService.getLargestWorkFlowByEmail(email);


            return  ResponseEntity.ok().body(employeeStatusDomain);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }
}
