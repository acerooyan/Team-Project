package com.example.emrestserver.controller;

import com.example.emrestserver.domains.visaStatus.EmployeeStatusDomain;
import com.example.emrestserver.domains.visaStatus.HrVisaStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@RestController
@RequestMapping("/jwt")
public class VisaStatusController {

    @Autowired
    EmployeeService1 employeeService1;

    @Autowired
    EmployeeVisaService employeeVisaService;

    @Autowired
    HrVisaService2 hrVisaService2;

    @Autowired
    HrVisaStatusService hrVisaStatusService;
    @Autowired
    RegisterService registerService;

    @GetMapping("/hr/visaStatus")
    public ResponseEntity<HrVisaStatusDomain> getAllVisaStatus() {
        HrVisaStatusDomain hrVisaStatusDomain = null;
        try{
            //todo: return domain object and put int in body.
            hrVisaStatusService.mainService();
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
            Gson g = new Gson();
            hrVisaStatusDomain = g.fromJson(model,HrVisaStatusDomain.class);
            String email = hrVisaStatusDomain.email;
            String workFlowStatus = hrVisaStatusDomain.workflowStatus;

            if(workFlowStatus.equals("reject")){
                //update comment
                employeeService1.updateWorkFlowByType(hrVisaStatusDomain.getCurrentStep(),email,hrVisaStatusDomain.getComment(),hrVisaStatusDomain.getWorkflowStatus());
            }else{
                hrVisaService2.buildPerson(hrVisaStatusDomain,email);

                //update visastatus
                employeeService1.updateVisaStatus(email,hrVisaStatusDomain.visa);

                //update employee
                Employee employee = employeeService1.getEmpolyeeByEmail(email);
                employee.setStartDate(Date.valueOf(hrVisaStatusDomain.getStartDate()));
                employee.setEndDate(Date.valueOf(hrVisaStatusDomain.getEndDate()));

                //add workflow
                hrVisaService2.addApplicationWorkFlow(employee,hrVisaStatusDomain.getNextStep());

            }

            //update Workflow
            employeeService1.updateWorkFlowByType(hrVisaStatusDomain.getCurrentStep(),email,"",hrVisaStatusDomain.getWorkflowStatus());



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
    public ResponseEntity<EmployeeStatusDomain> updateWorkFlowAndFile(@RequestPart(value = "model") String model,@RequestPart(value = "file") MultipartFile file) {
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
