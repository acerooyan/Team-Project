package com.example.emrestserver.dao;

import com.example.emrestserver.entity.ApplicationWorkFlow;
import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.VisaStatus;
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

    public ApplicationWorkFlow getCurrentStep(Integer employeeId){
        Session session = getCurrentSession();
        Query getVisaCurrentByEmployeeId = session.createQuery("From ApplicationWorkFlow awf WHERE awf.employee.id= :id order by awf.id desc");
        getVisaCurrentByEmployeeId.setParameter("id", employeeId);
        List<ApplicationWorkFlow> applicationWorkFlows = (List<ApplicationWorkFlow>)getVisaCurrentByEmployeeId.getResultList();
        return applicationWorkFlows.get(0);
    }

    public ApplicationWorkFlow getPrevStep(Integer employeeId){
        Session session = getCurrentSession();
        Query getVisaCurrentByEmployeeId = session.createQuery("From ApplicationWorkFlow awf WHERE awf.employee.id= :id order by awf.id desc");
        getVisaCurrentByEmployeeId.setParameter("id", employeeId);
        List<ApplicationWorkFlow> applicationWorkFlows = (List<ApplicationWorkFlow>)getVisaCurrentByEmployeeId.getResultList();
        return applicationWorkFlows.get(1);

    }



}
