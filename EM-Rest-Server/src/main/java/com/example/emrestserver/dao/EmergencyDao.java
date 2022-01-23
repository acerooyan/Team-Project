package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmergencyDao {

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
<<<<<<< HEAD:EM-Rest-Server/src/main/java/com/example/emrestserver/dao/EmployeeDao2.java

    public PersonalDocument[] getDocumentByEmployeeId(Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM  PersonalDocument p WHERE p.employee.id = :id ORDER BY id DESC");
        query.setParameter("id",id);
        List<PersonalDocument> personalDocumentList= (List<PersonalDocument>) query.getResultList();

        PersonalDocument[] personalDocuments = new PersonalDocument[personalDocumentList.size()];
        personalDocuments = personalDocumentList.toArray(personalDocuments);

        return personalDocuments;
    }
=======
>>>>>>> f7544bbf5e2a9f94d7f0752011e219e092134a3d:EM-Rest-Server/src/main/java/com/example/emrestserver/dao/EmergencyDao.java
}
