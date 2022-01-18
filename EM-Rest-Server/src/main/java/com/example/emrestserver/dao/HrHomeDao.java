package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HrHomeDao {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public List<Employee> getAllEmployees(){
        Session session = getCurrentSession();
        Query getAllEmployees = session.createQuery("FROM Employee");
        List<Employee> employeeList = (List<Employee>)getAllEmployees.getResultList();
        return employeeList;

    }


    @Transactional
    public List<Employee> getEmployeeListWithVisaStatusActive(){
        Session session = getCurrentSession();
        Query getAllEmployees = session.createQuery("FROM Employee JOIN FETCH ALL PROPERTIES");
        List<Employee> employeeList = (List<Employee>) getAllEmployees.getResultList();
        return employeeList;

    }

}
