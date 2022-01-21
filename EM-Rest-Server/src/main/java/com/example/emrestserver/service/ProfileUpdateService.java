package com.example.emrestserver.service;

import com.example.emrestserver.dao.PersonDao;
import com.example.emrestserver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
