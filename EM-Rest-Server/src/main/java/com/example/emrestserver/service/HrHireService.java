package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.domains.hire.HireDomain;
import com.example.emrestserver.domains.visaStatus.HrVisaStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HrHireService {
    @Autowired
    EmployeeDao employeeDao;

    @Transactional
    public HireDomain[] mainService(){
        // return
        Employee[] employees = employeeDao.getAllEmployees();

        List<HireDomain> hireDomains = new ArrayList<>();
        for(int i = 0; i < employees.length; i++){
            System.out.println(employees[i]);
            Person person = employees[i].getPerson();
            StringBuilder sb = new StringBuilder();
            sb.append(person.getFirstname());
            sb.append(" ");
            if(person.getMiddleName()!= null){
                sb.append(person.getMiddleName());
                sb.append(" ");
            }

            sb.append(person.getLastname());

            ApplicationWorkFlow applicationWorkFlow = employeeDao.getOnBoardingByEmployeeId(employees[i].getId());

            if(applicationWorkFlow != null)
            {

                HireDomain hireDomain = HireDomain.builder()
                        .email(employees[i].getPerson().getEmail())
                        .fullName(sb.toString())
                        .workAuthorization(employees[i].getVisaStatus().getVisaType())
                        .type("onBoarding")
                        .status(applicationWorkFlow.getStatus())
                        .build();
                hireDomains.add(hireDomain);
            }

        }
        HireDomain[] hireDomainArr = new HireDomain[hireDomains.size()];
        hireDomains.toArray(hireDomainArr);
        return hireDomainArr;

    }

}
