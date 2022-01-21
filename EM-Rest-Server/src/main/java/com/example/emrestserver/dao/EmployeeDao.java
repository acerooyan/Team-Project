package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
    @Autowired
    protected SessionFactory sessionFactory;
    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Employee getEmployeeById(Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Employee E WHERE id ="+ id);
        return (Employee) query.getSingleResult();

    }

    public Employee getEmployeeByEmail(String email){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Employee e WHERE e.person.email = :email");
        query.setParameter("email",email);
        return (Employee) query.getSingleResult();
    }

}
