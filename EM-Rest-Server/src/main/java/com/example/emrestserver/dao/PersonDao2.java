package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Contact;
import com.example.emrestserver.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao2 {
    @Autowired
    protected SessionFactory sessionFactory;

    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void updateAddress(Address address){
        Session session = getCurrentSession();
        session.merge(address);
    }

    public void updatePerson(Person person){
        Session session = getCurrentSession();
        session.merge(person);
    }

    public void updateContact(Contact contact){
        Session session = getCurrentSession();
        session.merge(contact);
    }
}
