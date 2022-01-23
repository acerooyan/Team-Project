package com.example.emrestserver.service;

import com.example.emrestserver.dao.HrVisaStatusDao;
import com.example.emrestserver.domains.combined.HrVisaStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class HrVisaStatusService {

    @Autowired
    HrVisaStatusDao hrVisaStatusDao;
    // focus on opt only

    public HrVisaStatusDomain[] mainService(){
        // get employees table whose visa status is opt
        Employee[] employees = hrVisaStatusDao.getAllEmployeeWithOpt();
        // get visaStatus currentStep
        for(int i = 0; i < employees.length; i ++){
            // get visaStatus By EmployeeId
            ApplicationWorkFlow currentStep = hrVisaStatusDao.getCurrentStep(employees[i].getId());
            // get next step:



        }
    }

    public String nextStep(Employee employee, String currentStep){
        if(currentStep.equals("onBoarding")){
            return "OPT Receipt";
        } else if (currentStep.equals("OPT Receipt")){
            return "OPT EAD";
        }else if(currentStep.equals("OPT EAD")){

        }
    }



}
