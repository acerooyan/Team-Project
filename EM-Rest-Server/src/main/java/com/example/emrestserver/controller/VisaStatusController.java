package com.example.emrestserver.controller;

import com.example.emrestserver.constant.JwtConstant;
import com.example.emrestserver.domains.visaStatus.EmployeeStatusDomain;
import com.example.emrestserver.domains.visaStatus.HrVisaStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.VisaStatus;
import com.example.emrestserver.security.util.CookieUtil;
import com.example.emrestserver.security.util.JwtUtil;
import com.example.emrestserver.service.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@RestController
@RequestMapping("/jwt")
public class VisaStatusController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeVisaService employeeVisaService;

    @Autowired
    HrVisaService2 hrVisaService2;

    @Autowired
    HrVisaStatusService hrVisaStatusService;
    @Autowired
    RegisterService registerService;

    @Autowired
    private AwsService awsService;

    @Autowired
    private VisaStatusService visaStatusService;

    @Autowired
    private PersonalDocumentService personalDocumentService;

    private static final Logger logger = LoggerFactory.getLogger(VisaStatusController.class);

    @GetMapping("/hr/visaStatus")
    public ResponseEntity<HrVisaStatusDomain[]> getAllVisaStatus() {

        try{
            //todo: return domain object and put int in body.
            HrVisaStatusDomain[] hrVisaStatusDomain = hrVisaStatusService.mainService();
            return  ResponseEntity.ok().body(hrVisaStatusDomain);
        }catch (Exception e){
//            System.out.println("/hr/visaStatus error catch");
            logger.error("error catch");
            e.printStackTrace();
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
                employeeService.updateWorkFlowByType(hrVisaStatusDomain.getCurrentStep(),email,hrVisaStatusDomain.getComment(),hrVisaStatusDomain.getWorkflowStatus());
            }else{
                hrVisaService2.buildPerson(hrVisaStatusDomain,email);

                //update visastatus
                employeeService.updateVisaStatus(email,hrVisaStatusDomain.visa);

                //update employee
                Employee employee = employeeService.getEmpolyeeByEmail(email);
                employee.setStartDate(Date.valueOf(hrVisaStatusDomain.getStartDate()));
                employee.setEndDate(Date.valueOf(hrVisaStatusDomain.getEndDate()));

                //add workflow
                String type = hrVisaStatusDomain.getCurrentStep();
                if(type.equalsIgnoreCase("opt ead")){
                    hrVisaService2.addApplicationWorkFlow(employee,"I-983");
                }else{
                    hrVisaService2.addApplicationWorkFlow(employee,hrVisaStatusDomain.getNextStep());
                }



                employeeService.updateWorkFlowByType(hrVisaStatusDomain.getCurrentStep(),email,"",hrVisaStatusDomain.getWorkflowStatus());

            }





            return  ResponseEntity.ok().body(hrVisaStatusDomain);
        }catch (Exception e){
//            System.out.println("/hr/visaStatus error catch");
            logger.error("Error Catch");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/em/visaStatus")
    public ResponseEntity<EmployeeStatusDomain> getWorkFlow(ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);

        EmployeeStatusDomain employeeStatusDomain = null;
        try{
            //todo: return work flow

            employeeStatusDomain = employeeVisaService.mainService(email);
            return  ResponseEntity.ok().body(employeeStatusDomain);
        }catch (Exception e){
//            System.out.println("/em/visaStatus error catch");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/em/visaStatus")
    public ResponseEntity<EmployeeStatusDomain> updateWorkFlowAndFile(ServletRequest servletRequest,@RequestPart(value = "model") String model,@RequestPart(value = "file") MultipartFile file) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        String email = JwtUtil.getSubjectFromJwt(token);

        EmployeeStatusDomain employeeStatusDomain = null;
        try{
            //todo: update database workflow and document
            Gson g = new Gson();
            employeeStatusDomain = g.fromJson(model,EmployeeStatusDomain.class);
            String fileName = awsService.uploadFile(file);
//            System.out.println(fileName);
//            System.out.println(employeeStatusDomain);
            if(employeeStatusDomain.getStatus().equalsIgnoreCase("reject")){
                personalDocumentService.updatePersonalDocument(fileName, employeeService.getEmpolyeeByEmail(email));

            }else{

                //update visastatus active if it is opt ead and opt stem ead
                if(employeeStatusDomain.currentStep.equalsIgnoreCase("OPT EAD")
                        && employeeStatusDomain.currentStep.equalsIgnoreCase("OPT STEM EAD") ){

                    visaStatusService.updateVisaStatusActiveById(employeeService.getEmpolyeeByEmail(email).getId(),"1");
                }

                personalDocumentService.buildDocument(fileName, employeeService.getEmpolyeeByEmail(email));

            }

            employeeService.updateWorkFlowByType(employeeStatusDomain.getNextStep(),email,"","pending");

            return  ResponseEntity.ok().body(employeeStatusDomain);
        }catch (Exception e){
//            System.out.println("/em/visaStatus error catch");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
