package com.example.emrestserver.dao;

import com.example.emrestserver.controller.OnboardController;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PersonalDocumentDao {
    @Autowired
    protected SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(PersonalDocumentDao.class);

    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public PersonalDocument addPersonalDocument( PersonalDocument personalDocument){
        Session session = getCurrentSession();

        logger.info("DAO received document" + personalDocument);

//        System.out.println("DAO received document" + personalDocument);

        Integer personalDocumentId = (Integer) session.save(personalDocument);
//        System.out.println("DAO saved document");
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

    public PersonalDocument getPersonalDocumentByTitle( String title,Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM PersonalDocument WHERE title = :title and id = :id");
        query.setParameter("title",title);
        query.setParameter("id",id);

        return (PersonalDocument) query.getSingleResult();
    }



}
