package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusService {
    @Autowired
    EmployeeDao employeeDao;

    @Transactional
    public ApplicationWorkFlow getOnboardApplicationWorkFlow(Integer employeeId){
        return employeeDao.getOnboardApplicationWorkFlowById(employeeId);
    }

}
