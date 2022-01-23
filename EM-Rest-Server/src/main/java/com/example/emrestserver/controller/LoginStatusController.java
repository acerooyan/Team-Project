package com.example.emrestserver.controller;

import com.example.emrestserver.constant.JwtConstant;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;

import com.example.emrestserver.entity.VisaStatus;
import com.example.emrestserver.security.util.CookieUtil;
import com.example.emrestserver.security.util.JwtUtil;
import com.example.emrestserver.service.EmployeeService;


import com.example.emrestserver.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginStatusController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    StatusService statusService;

    @GetMapping("/status")
    public ResponseEntity<Object> getStatus(ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);

//        try{
//            String status = null;
            //todo: get status from applicationworkflow
            // get visa status
            Employee employee = employeeService.getEmpolyeeByEmail(email);
            ApplicationWorkFlow applicationWorkFlow = statusService.getOnboardApplicationWorkFlow(employee.getId());
            String status = applicationWorkFlow.getStatus();
            if(status != null && status.equals("complete")){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.status(403).body("You application is still on pending!");
            }

//        }catch (Exception e){
//            System.out.println("error catch");
//            return ResponseEntity.internalServerError().build();
//        }
    }
}
