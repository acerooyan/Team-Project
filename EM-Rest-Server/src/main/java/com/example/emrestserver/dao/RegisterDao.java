package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class RegisterDao {

    @Autowired
    protected SessionFactory sessionFactory;
    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }


    public Person addPerson(Person p){

        Session session = getCurrentSession();
        Query findAllPerson = session.createQuery("FROM Person");

        Integer personId = findAllPerson.getResultList().size();
        p.setId(personId);
        session.merge(p);
        // check if add successfully.
        Query findPersonById = session.createQuery("FROM Person p WHERE p.id = :id");
        findPersonById.setParameter("id", p.getId());
        Person p1 = (Person)findPersonById.getSingleResult();
        System.out.println(p.getId()+" == "+ p1.getId() );
        return p1;

    }

}
