package com.example.emrestserver.service;

import com.example.emrestserver.dao.RegisterDao;
import com.example.emrestserver.domains.combined.ContactInfoDomain;
import com.example.emrestserver.domains.combined.MergeDomain;
import com.example.emrestserver.domains.standalone.BasicInfoDomain;
import com.example.emrestserver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service("registerService")
public class RegisterService {

    @Autowired
    RegisterDao registerDao;
    @Transactional
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

    @Transactional
    public Person convertBasicInfoToPerson(BasicInfoDomain basicInfoDomain, ContactInfoDomain contactInfoDomain){
//        String dateString = basicInfoDomain.getDOB();
//        System.out.println(dateString);
//        Date date = Date.valueOf(basicInfoDomain.getDOB());
        Person p = Person.builder().firstname(basicInfoDomain.getFirstName())
                .lastname(basicInfoDomain.getLastName())
                .middleName(basicInfoDomain.getMiddleName())
                .email(contactInfoDomain.getEmail())
                .cellPhone(contactInfoDomain.getCellphone())
                .alternatePhone(contactInfoDomain.getAlternatePhone())
                .ssn(basicInfoDomain.getSsn())
                .dob(Date.valueOf(basicInfoDomain.getDob()))
                .userId(12345).build();
//        Person newP = addPerson(p);
        return p;
    }
    @Transactional
    public Person addPerson(Person person){
        return registerDao.addPerson(person);
    }

//    private Integer addAddress(Address address){
//        return 0;
//    }
//    private Integer addPerson( person){
//        return 0;
//    }
//    private Integer addPerson(Person person){
//        return 0;
//    }

}
