package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDao {
    @Autowired
    protected SessionFactory sessionFactory;

    private Integer addPerson(Person p){

    }

}
