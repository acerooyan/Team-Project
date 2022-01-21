package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Address;
import com.example.emrestserver.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDao {
    @Autowired
    protected SessionFactory sessionFactory;
    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public Employee getEmployeeById(Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Employee E WHERE id ="+ id);
        return (Employee) query.getSingleResult();

    }
    @Transactional
    public Employee getEmployeeByEmail(String email){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Employee e WHERE e.person.email = :email");
        query.setParameter("email",email);
        return (Employee) query.getSingleResult();
    }

    @Transactional
    public Address[] getAddressByPersonId(Integer personId){
        Session session = getCurrentSession();
        Query getAddress = session.createQuery("From Address WHERE Person.id =:id");
        getAddress.setParameter("id", personId);
        List<Address> addressList = (List<Address>)getAddress.getResultList();
        Address[] addressArray = new Address[addressList.size()];
        addressList.toArray(addressArray);
        return addressArray;
    }

}
