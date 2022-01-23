package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Contact;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao2 {
    @Autowired
    protected SessionFactory sessionFactory;
    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Contact getEmergencyByEmployeeId(Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Contact c WHERE c.employee = :id and c.isEmergency = 1");
        query.setParameter("id",id);
        return (Contact) query.getSingleResult();
    }

    public PersonalDocument[] getDocumentByEmployeeId(Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM  PersonalDocument p WHERE p.employee.id = :id ORDER BY id DESC");
        query.setParameter("id",id);
        List<PersonalDocument> personalDocumentList= (List<PersonalDocument>) query.getResultList();

        PersonalDocument[] personalDocuments = new PersonalDocument[personalDocumentList.size()];
        personalDocuments = personalDocumentList.toArray(personalDocuments);

        return personalDocuments;
    }
}
