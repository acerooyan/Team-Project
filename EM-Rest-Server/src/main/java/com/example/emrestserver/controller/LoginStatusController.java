package com.example.emrestserver.controller;

import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.service.EmployeeService;
import com.example.emrestserver.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginStatusController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    StatusService statusService;

    @GetMapping("/status")
    public ResponseEntity<Object> getStatus() {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
//        String email = JwtUtil.getSubjectFromJwt(token);
        String email = "jamesh970327@gmail.com";
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
                return ResponseEntity.status(666).build();
            }

//        }catch (Exception e){
//            System.out.println("error catch");
//            return ResponseEntity.internalServerError().build();
//        }
    }
}
