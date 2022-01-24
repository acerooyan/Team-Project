package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.dao.HrVisaStatusDao;
import com.example.emrestserver.domains.visaStatus.HrVisaStatusDomain;
import com.example.emrestserver.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Service
public class HrVisaStatusService {

    @Autowired
    HrVisaStatusDao hrVisaStatusDao;

    @Autowired
    EmployeeDao employeeDao;
    // focus on opt only

    @Transactional
    public HrVisaStatusDomain[] mainService(){
        String currentStep;
        String nextStep;
        // get employees table whose visa status is opt
        Employee[] employees = hrVisaStatusDao.getAllEmployeeWithOpt();
        // get visaStatus currentStep
        HrVisaStatusDomain[] hrVisaStatusDomains = new HrVisaStatusDomain[employees.length];
        for(int i = 0; i < employees.length; i ++){
            PersonalDocument[] personalDocuments = employeeDao.getDocumentByEmployeeId(employees[i].getId());
            String[] documentArr = new String[personalDocuments.length];
            for(int j = 0; j < documentArr.length; j++){
                documentArr[j] = personalDocuments[j].getPath();
            }

            Person person = employees[i].getPerson();

            StringBuilder sb = new StringBuilder();
            sb.append(person.getFirstname());
            sb.append(" ");
            if(person.getMiddleName()!= null){
                sb.append(person.getMiddleName());
                sb.append(" ");
            }
            sb.append(person.getLastname());

            ApplicationWorkFlow currentStepAWF = hrVisaStatusDao.getCurrentStep(employees[i].getId());

            currentStep = currentStepAWF.getType();
            if(currentStepAWF.getStatus().equals("pending")){
                nextStep = getNextStep(currentStepAWF);
            }else{
                nextStep = "complete";
            }
            HrVisaStatusDomain hrVisaStatusDomain = HrVisaStatusDomain.builder()
                    .email(employees[i].getPerson().getEmail())
                    .fullName(sb.toString())
                    .visa(employees[i].getVisaStatus().getVisaType())
                    .startDate(employees[i].getVisaStartDate().toString())
                    .endDate(employees[i].getVisaEndDate().toString())
                    .dayLeft(daysLeft(employees[i].getVisaEndDate()))
                    .documents(documentArr)
                    .currentStep(currentStep)
                    .nextStep(nextStep)
                    .comment("")
                    .workflowStatus("")
                    .build();
            hrVisaStatusDomains[i] = hrVisaStatusDomain;
        }
        return hrVisaStatusDomains;
    }
    @Transactional
    public String getNextStep(ApplicationWorkFlow current){
        // 不需要检查 EAD 过期问题
        String currentStep = current.getType();

        switch (currentStep) {
            case "onBoarding":
                return "OPT Receipt";
            case "OPT Receipt":
                return "OPT EAD";
            case "OPT EAD":
            case "OPT STEM EAD":
                return "complete";
            case "I-983":
                return "I-20";
            case "I-20":
                return "OPT STEM Receipt";
            case "OPT STEM Receipt":
                return "OPT STEM EAD";
        }
        return "";
    }
    @Transactional
    public Integer daysLeft(Date endDate){

        LocalDate start = LocalDate.now();
        LocalDate end = endDate.toLocalDate();
        Integer days = (int) ChronoUnit.DAYS.between(start, end);
        return days;
    }


}
