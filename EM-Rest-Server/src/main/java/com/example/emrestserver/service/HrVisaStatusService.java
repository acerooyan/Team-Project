package com.example.emrestserver.service;

import com.example.emrestserver.dao.HrVisaStatusDao;
import com.example.emrestserver.domains.combined.HrVisaStatusDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrVisaStatusService {

    @Autowired
    HrVisaStatusDao hrVisaStatusDao;
    // focus on opt only

    public HrVisaStatusDomain[] getAllEmployees(){
        // get employees table whose visa status is opt
        hrVisaStatusDao.getAllEmployeeWithOpt();
        return null;


    }
}
