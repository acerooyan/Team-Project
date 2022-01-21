package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Contact;
import com.example.emrestserver.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
