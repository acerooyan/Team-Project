package com.example.emrestserver.service;

import com.example.emrestserver.domains.visaStatus.HrVisaStatusDomain;
import com.example.emrestserver.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HrHireService {

    @Transactional
    public HrVisaStatusDomain[] mainService(){
        // return
        Employee[] employees;
        HrVisaStatusDomain[] hrVisaStatusDomains;
        return null;
    }

}
