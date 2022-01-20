package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Person;
import com.example.emrestserver.entity.VisaStatus;
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

        System.out.println(p);
        Integer personId = (Integer)session.save(p);

        // check if add successfully.
        Query findPersonById = session.createQuery("FROM Person p WHERE p.id = :id");
        findPersonById.setParameter("id", personId);
        System.out.println(personId);
        Person p1 = (Person)findPersonById.getSingleResult();
        System.out.println(personId+" == "+ p1.getId() );
        return p;

    }
    public void addAddress(Address address){
        Session session = getCurrentSession();
        Query findAllAddress = session.createQuery("FROM Address");
        Integer addressId = findAllAddress.getResultList().size()+1;
        address.setId(addressId);
        session.merge(address);
    }

    public VisaStatus addVisaStatus(VisaStatus visaStatus){
        Session session = getCurrentSession();
        Integer Id = (Integer) session.save(visaStatus);
        return getVisaStatusById(Id);

    }

    public VisaStatus getVisaStatusById(Integer id){
        Session session = getCurrentSession();
        Query findVisaStatus = session.createQuery("FROM VisaStatus Where id = :id");
        findVisaStatus.setParameter("id", id);
        return (VisaStatus) findVisaStatus.getSingleResult();
    }
}
