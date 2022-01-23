package com.example.emrestserver.controller;

import com.example.emrestserver.domain.EmployeeProfileDomain;
import com.example.emrestserver.domain.HrHomeDomain;
import com.example.emrestserver.service.HrHomeService;
import com.example.emrestserver.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api")
public class HomeController {

    @Autowired
    HrHomeService hrHomeService;
    private PersonService personService;

    @Autowired
    public HomeController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/hr/home")
    public ResponseEntity<List<HrHomeDomain>> hrHome() {
        //hard coded data
//        ApplicationWorkFlow applicationWorkFlow = new ApplicationWorkFlow(null,null,new Date(),new Date(),"pending","lol","opt");

//        try{
        //get application list from database

        List<HrHomeDomain> hrHomeDomainList = hrHomeService.mapDocumentWithEmployee();

//        }catch (Exception e){
//            System.out.println("error catch");
//            return ResponseEntity.internalServerError().build();
//        }
        return ResponseEntity.ok().body(hrHomeDomainList);
    }

    @GetMapping("/employee/home")
    public String employeeHome() {
        try {
            //add new person in database
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/employee/profiles")
    public ResponseEntity<EmployeeProfileDomain> getAllProfiles(@RequestBody EmployeeProfileDomain employeeProfileDomain) {
        EmployeeProfileDomain employeeProfileDomain1 = personService.getEmployees(employeeProfileDomain.getCurPage(), employeeProfileDomain.getTotalNum(), employeeProfileDomain.getMaxResult(), employeeProfileDomain.getEmail());
        return ResponseEntity.ok(employeeProfileDomain1);
    }
}
