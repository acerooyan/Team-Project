package com.example.emrestserver.service;

import com.example.emrestserver.dao.PersonDao;
import com.example.emrestserver.domains.profile.PersonalInfoDomain;
import com.example.emrestserver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service("profileUpdateService ")
public class ProfileUpdateService {

    @Autowired
    private PersonDao personDao;

    @Transactional
    public Person getPersonByEmail(String email){
        return personDao.getPersonByEmail(email);
    }

    @Transactional
    public Person updatePersonWithPerson(Person person){
        return personDao.updatePersonWithPerson(person);
    }

    @Transactional
    public Person buildPerson(PersonalInfoDomain personalInfoDomain,String email) throws ParseException {
        Person person = getPersonByEmail(email);

        //change name
        String fullName = personalInfoDomain.getFullName();
        String[] split = fullName.split("\\s+");
        person.setFirstname(split[0]);
        if(split.length == 2){
            person.setLastname(split[1]);
        }else{
            person.setMiddleName(split[1]);
            person.setFirstname(split[2]);
        }

        //change dob
        person.setDob(Date.valueOf(personalInfoDomain.getDob()));

        //change gender
        person.setGender(personalInfoDomain.getGender());

        //change ssn
        person.setSsn(personalInfoDomain.getSsn());

        return person;
    }

}
