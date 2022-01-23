package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.dao.HrVisaStatusDao;
import com.example.emrestserver.domains.visaStatus.EmployeeStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service("employeeVisaService")
public class EmployeeVisaService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    HrVisaStatusDao hrVisaStatusDao;

    public EmployeeStatusDomain mainService(String email){
        String current;
        String next;

        Employee employee = employeeDao.getEmployeeByEmail(email);

        ApplicationWorkFlow currentAWF = hrVisaStatusDao.getCurrentStep(employee.getId());
        ApplicationWorkFlow nextAWF;
        if(currentAWF.getStatus().equals("")){
            if (currentAWF.getType().equals("onBoarding")){
                current = "Get Started";
                next = currentAWF.getType();

            }else{
                nextAWF = currentAWF;
                currentAWF = hrVisaStatusDao.getPrevStep(employee.getId());
                current = currentAWF.getType();
                next = nextAWF.getType();
            }
        }else if(currentAWF.getType().equals("I-983") && currentAWF.getStatus().equals("")){
            // todo place to check expiration date
            if(daysLeft(employee.getVisaEndDate()) < 100){
//                current =
            }else{
                current = currentAWF.getType();
                next = "complete";
            }

        }
        String status = currentAWF.getStatus();

    }

    public ApplicationWorkFlow getLargestWorkFlowByEmail(String email){
        return employeeDao.getLargestWorkFlowByEmail(email);
    }

    public Integer daysLeft(Date endDate){

        LocalDate start = LocalDate.now();
        LocalDate end = endDate.toLocalDate();
        Integer days = (int) ChronoUnit.DAYS.between(start, end);
        return days;
    }
}
