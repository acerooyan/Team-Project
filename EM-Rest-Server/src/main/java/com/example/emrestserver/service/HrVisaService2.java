package com.example.emrestserver.service;

import com.example.emrestserver.dao.PersonDao;
import com.example.emrestserver.dao.RegisterDao;
import com.example.emrestserver.domains.profile.PersonalInfoDomain;
import com.example.emrestserver.domains.visaStatus.HrVisaStatusDomain;
import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;

@Service("hrVisaService2  ")
public class HrVisaService2 {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RegisterDao registerDao;

    @Transactional
    public Person getPersonByEmail(String email){
        return personDao.getPersonByEmail(email);
    }

    @Transactional
    public Person updatePersonWithPerson(Person person){
        return personDao.updatePersonWithPerson(person);
    }

    @Transactional
    public Person buildPerson(HrVisaStatusDomain hrVisaStatusDomain, String email) throws ParseException {
        Person person = getPersonByEmail(email);

        //change name
        String fullName =hrVisaStatusDomain.getFullName();
        String[] split = fullName.split("\\s+");
        person.setFirstname(split[0]);
        if(split.length == 2){
            person.setLastname(split[1]);
        }else{
            person.setMiddleName(split[1]);
            person.setFirstname(split[2]);
        }

        return updatePersonWithPerson(person);
    }

    @Transactional
    public ApplicationWorkFlow addApplicationWorkFlow(Employee employee,String type){
        ApplicationWorkFlow applicationWorkFlow = ApplicationWorkFlow
                .builder()
                .employee(employee)
                .createdDate(new Date(System.currentTimeMillis()))
                .modificationDate(new Date(System.currentTimeMillis()))
                .status("")
                .type(type)
                .build();
        return registerDao.addApplicationWorkFlow(applicationWorkFlow);
    }
}
