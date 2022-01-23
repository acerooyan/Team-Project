package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class HrVisaStatusDao {
    @Autowired
    protected SessionFactory sessionFactory;

    protected  final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Employee[] getAllEmployeeWithOpt(){
        Session session = getCurrentSession();
        Query getAllEmployeeWithVisaStatus = session.createQuery("FROM Employee e WHERE e.visaStatus.visaType = 'OPT'");
        List<Employee> employees = (List<Employee>) getAllEmployeeWithVisaStatus.getResultList();
        Employee[] employeeArr = new Employee[employees.size()];
        employees.toArray(employeeArr);

        return employeeArr;
    }

    // generate next step(only String not creating the application work flow;



}
