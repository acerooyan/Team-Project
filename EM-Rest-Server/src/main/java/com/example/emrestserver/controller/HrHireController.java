package com.example.emrestserver.controller;

import com.example.emrestserver.domains.hire.HireDomain;
import com.example.emrestserver.domains.profile.ProfileDomain;
import com.example.emrestserver.domains.visaStatus.EmployeeStatusDomain;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Mail;
import com.example.emrestserver.service.EmployeeService;
import com.example.emrestserver.service.HrHireService;
import com.example.emrestserver.service.HrVisaService2;
import com.example.emrestserver.service.email.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


    @Autowired
    EmailService emailService;

    @Autowired
    HrVisaService2 hrVisaService2;

    private static final Logger log4j = LogManager.getLogger();

    @GetMapping("hr/hire")
    public ResponseEntity<HireDomain[]> getAll() {

        try{

            HireDomain[] hireDomains = hrHireService.mainService();


            return  ResponseEntity.ok().body(hireDomains);
        }catch (Exception e){
           e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/hr/profile/byemail")
    public ResponseEntity<ProfileDomain> getProfilebyemail(ServletRequest servletRequest, @RequestPart(value = "model") String model ) {

        String email = model;
        try{
            //get application list from database
            //List<HrProfilerDomain> hrProfilerDomainList = hrProfilerService.mapDocumentWithEmployee();
            ProfileDomain profileDomain = employeeService.getDataReady(email);
            return  ResponseEntity.ok().body(profileDomain);
        }catch (Exception e){
//            System.out.println("error catch");
            log4j.error("Error Catch");
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("/hr/hire/decision")
    public ResponseEntity<EmployeeStatusDomain> changeWorkFlow( @RequestPart(value = "email") String email,
                                                                @RequestPart(value = "status") String status) {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
//        String email = JwtUtil.getSubjectFromJwt(token);

        try{
            //"reject";
            //complete
            Employee employee = employeeService.getEmpolyeeByEmail(email);
            employeeService.updateWorkFlowByType("onBoarding",email,"nah",status);
            hrVisaService2.addApplicationWorkFlow(employee,"OPT Receipt");
            return  ResponseEntity.ok().build();
        }catch (Exception e){
//            System.out.println("error catch");
            return ResponseEntity.internalServerError().build();
        }
    }



    @PostMapping("/hr/decision/sendNotification")
    public ResponseEntity<String> sendNotification(@RequestPart("email") String email, @RequestPart("comment") String comment, @RequestPart("approved") String approved){
        Mail mail = new Mail();
        mail.setMailTo(email);
        mail.setContentType("text/html");
        mail.setMailSubject("Application Notification");

        if(approved.equals("complete"))
        {
            mail.setMailContent("Congratulation! You application is approved, please log in.\n" + "Hr Comment:" + comment);
        }
        else
        {
            mail.setMailContent("Thank you so much for your interest our Company,  we’ve decided to move forward with another candidate.\n"+ "Hr Comment:" + comment);
        }

        emailService.sendEmail(mail);
        return ResponseEntity.ok().build();
    }






}
