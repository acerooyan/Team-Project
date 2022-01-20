package com.example.emrestserver.service;

import com.example.emrestserver.domain.BasicInfoDomain;
import com.example.emrestserver.domain.ContactInfoDomain;
import com.example.emrestserver.domain.MergeDomain;
import com.example.emrestserver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("registerService")
public class RegisterService {

    @Autowired
    RegisterDao registerDao;

    public void mainService(MergeDomain mergeDomain){
        // basicInfoDomain contains:
        BasicInfoDomain basicInfoDomain = mergeDomain.getBasicInfoDomain();
        ContactInfoDomain contactInfoDomain = mergeDomain.getContactInfoDomain();

        //transfer domain to entity with empty id, then return the id to save the rest of the entity
        /* order:
        * 1. Person(who is employee)
        * 2. Addresses
        * 3. person(who is/are contact)
        * 4. visaStatus(no employeeId needed)
        * 5. Employee (get personal ID and Visastatus Id house set to null)
        * 6. PersonalDocument (employeeId needed)
        * */
    }

    private Integer addPerson(Person person){
        return 0;
    }
}
