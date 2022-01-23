package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PersonalDocumentDao {
    @Autowired
    protected SessionFactory sessionFactory;

    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public PersonalDocument addPersonalDocument( PersonalDocument personalDocument){
        Session session = getCurrentSession();

        System.out.println("DAO received document" + personalDocument);

        Integer personalDocumentId = (Integer) session.save(personalDocument);
        System.out.println("DAO saved document");
        System.out.println(personalDocument);
        return personalDocument;
    }

    public Employee getEmployee(){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Employee WHERE id = 1");
        return (Employee) query.getSingleResult();

    }


    public PersonalDocument updatePersonalDocument( PersonalDocument personalDocument){
        Session session = getCurrentSession();


        session.merge(personalDocument);
        return personalDocument;
    }



}
