package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {
    @Autowired
    protected SessionFactory sessionFactory;

    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Person getPersonByEmail(String email){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Person p WHERE p.email = :email");
        query.setParameter("email",email);
        Person person = (Person) query.getSingleResult();
        //System.out.println(person.getContactList().size());
        return person;
    }

    public Person updatePersonWithPerson(Person person){
        Session session = getCurrentSession();
        return (Person) session.merge(person);
    }

}
