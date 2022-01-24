package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeVisaService")
public class EmployeeVisaService {

    @Autowired
    EmployeeDao employeeDao;

    @Transactional
    public ApplicationWorkFlow getLargestWorkFlowByEmail(String email){
        return employeeDao.getLargestWorkFlowByEmail(email);
    }

}
