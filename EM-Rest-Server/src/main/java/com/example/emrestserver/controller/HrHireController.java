package com.example.emrestserver.controller;

import com.example.emrestserver.domains.hire.HireDomain;
import com.example.emrestserver.domains.profile.ProfileDomain;
import com.example.emrestserver.domains.visaStatus.EmployeeStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.service.EmployeeService;
import com.example.emrestserver.service.HrHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@RestController
@RequestMapping("/jwt")
public class HrHireController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HrHireService hrHireService;

    @GetMapping("hr/hire")
    public ResponseEntity<HireDomain[]> getAll() {

        try{
            //todo: return list of employees in hr hire page
            HireDomain[] hireDomains = hrHireService.mainService();

            //todo: change null to required domain
            return  ResponseEntity.ok().body(hireDomains);
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/hr/profile/byemail")
    public ResponseEntity<ProfileDomain> getProfilebyemail(ServletRequest servletRequest, @RequestPart(value = "model") String model ) {

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


    @PutMapping("/hr/hire")
    public ResponseEntity<EmployeeStatusDomain> changeWorkFlow( @RequestPart(value = "email") String email,@RequestPart(value = "comment") String comment,
                                                                @RequestPart(value = "status") String status) {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
//        String email = JwtUtil.getSubjectFromJwt(token);

        try{
            //todo: update work flow

            employeeService.updateWorkFlowByType("onBoarding",email,comment,status);

            return  ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }





}
