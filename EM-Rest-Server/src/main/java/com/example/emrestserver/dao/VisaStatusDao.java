package com.example.emrestserver.dao;

import com.example.emrestserver.entity.VisaStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class VisaStatusDao {
    @Autowired
    protected SessionFactory sessionFactory;
    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public VisaStatus updateVisaStatusActiveById(Integer id,String active){
        Session session = getCurrentSession();
        Query findVisaStatus = session.createQuery("FROM VisaStatus Where id = :id");
        findVisaStatus.setParameter("id", id);
        VisaStatus visaStatus = (VisaStatus) findVisaStatus.getSingleResult();
        visaStatus.setActive(active);

        session.merge(visaStatus);

        return visaStatus;
    }
}
