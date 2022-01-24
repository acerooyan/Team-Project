package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.domains.hire.HireDomain;
import com.example.emrestserver.domains.visaStatus.HrVisaStatusDomain;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HrHireService {
    @Autowired
    EmployeeDao employeeDao;

    @Transactional
    public HireDomain[] mainService(){
        // return
        Employee[] employees = employeeDao.getAllEmployees();

        HireDomain[] hireDomains = new HireDomain[employees.length];
        for(int i = 0; i < employees.length; i++){
            Person person = employees[i].getPerson();
            StringBuilder sb = new StringBuilder();
            sb.append(person.getFirstname());
            sb.append(" ");
            if(person.getMiddleName()!= null){
                sb.append(person.getMiddleName());
                sb.append(" ");
            }

            sb.append(person.getLastname());

            HireDomain hireDomain = HireDomain.builder()
                    .email(employees[i].getPerson().getEmail())
                    .fullName(sb.toString())
                    .workAuthorization(employees[i].getVisaStatus().getVisaType())
                    .type("onBoarding")
                    .status(employeeDao.getOnBoardingByEmployeeId(employees[i].getId()).getStatus())
                    .build();
            hireDomains[i] = hireDomain;
        }
        return hireDomains;

    }

}
