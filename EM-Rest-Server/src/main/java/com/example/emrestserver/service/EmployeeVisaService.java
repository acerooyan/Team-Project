package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.dao.HrVisaStatusDao;
import com.example.emrestserver.domains.visaStatus.EmployeeStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service("employeeVisaService")
public class EmployeeVisaService {

    @Autowired
    EmployeeDao employeeDao;


    @Autowired
    HrVisaStatusDao hrVisaStatusDao;

    @Transactional
    public EmployeeStatusDomain mainService(String email){
        String current = "";
        String next = "";

        Employee employee = employeeDao.getEmployeeByEmail(email);

        ApplicationWorkFlow currentAWF = hrVisaStatusDao.getCurrentStep(employee.getId());
        ApplicationWorkFlow nextAWF;
        if(currentAWF.getStatus().equals("")){
            if (currentAWF.getType().equals("onBoarding")) {
                current = "Get Started";
                next = currentAWF.getType();
            }else if(currentAWF.getType().equals("I-983")){
                if(daysLeft(employee.getVisaEndDate()) < 100){
                    currentAWF = hrVisaStatusDao.getPrevStep(employee.getId());
                    nextAWF = currentAWF;
                    current = currentAWF.getType();
                    next = nextAWF.getType();
                }else{
                    currentAWF = hrVisaStatusDao.getPrevStep(employee.getId());
                    current = currentAWF.getType();
                    next = "complete";
                }

            }else{
                nextAWF = currentAWF;
                currentAWF = hrVisaStatusDao.getPrevStep(employee.getId());
                current = currentAWF.getType();
                next = nextAWF.getType();
            }

        }else if (currentAWF.getStatus().equals("pending")){
            current = currentAWF.getType();
            next = "complete";
        }else if(currentAWF.getStatus().equals("reject")){
            current = currentAWF.getType();
            next = currentAWF.getType();
        }else if(currentAWF.getStatus().equals("complete")){
            current = currentAWF.getType();
            next = "complete";
        }
        String status = currentAWF.getStatus();
        PersonalDocument[] personalDocuments = employeeDao.getDocumentByEmployeeId(employee.getId());
        String[] documentArr = new String[personalDocuments.length];
        for(int i  = 0; i < documentArr.length; i++){
            documentArr[i] = personalDocuments[i].getPath();
        }
        EmployeeStatusDomain employeeStatusDomain = EmployeeStatusDomain.builder()
                .currentStep(current)
                .nextStep(next)
                .comment(currentAWF.getComments())
                .status(status)
                .i983("i983.pdf")
                .i983Sample("i983Sample.pdf")
                .documents(documentArr)
                .build();
        return employeeStatusDomain;
    }


    @Transactional
    public ApplicationWorkFlow getLargestWorkFlowByEmail(String email){
        return employeeDao.getLargestWorkFlowByEmail(email);
    }

    @Transactional
    public Integer daysLeft(Date endDate){

        LocalDate start = LocalDate.now();
        LocalDate end = endDate.toLocalDate();
        Integer days = (int) ChronoUnit.DAYS.between(start, end);
        return days;
    }

}
