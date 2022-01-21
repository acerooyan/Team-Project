package com.example.emrestserver.service;

import com.example.emrestserver.dao.EmployeeDao2;
import com.example.emrestserver.domains.standalone.ContactEmergencyDomain;
import com.example.emrestserver.entity.Contact;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
public class EmployeeService2 {

    @Autowired
    EmployeeDao2 employeeDao2;

    @Transactional
    public Contact getEmergencyByEmployeeId(Integer id){
        return getEmergencyByEmployeeId(id);
    }

    @Transactional
    public PersonalDocument[] getDocumentByEmployeeId(Integer id){
        return employeeDao2.getDocumentByEmployeeId(id);
    }
}
