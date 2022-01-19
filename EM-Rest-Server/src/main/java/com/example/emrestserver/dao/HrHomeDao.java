package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.PersonalDocument;
import com.example.emrestserver.entity.VisaStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository("hrHomeDao")
public class HrHomeDao {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }




//    @Transactional
    public List<Employee> getAllEmployees(){
        Session session = getCurrentSession();
//        Query getAllEmployees = session.createQuery("FROM Employee e JOIN FETCH e.person");
        Query getAllEmployees = session.createQuery("FROM Employee e WHERE e.visaStatus.active=1");
        List<Employee> employeeList = (List<Employee>) getAllEmployees.getResultList();
        return employeeList;
    }


    public List<List<PersonalDocument>> getAllPersonalList(List<Integer> employeeIdList){
        List<List<PersonalDocument>> ans = new ArrayList<>();
        for(Integer employeeId: employeeIdList){
            ans.add(getPersonalDocumentListByEmployeeId(employeeId));
        }
        return ans;
    }

    public List<PersonalDocument> getPersonalDocumentListByEmployeeId(Integer employeeId){
        Session session = getCurrentSession();
        Query getPersonalDocumentListById = session.createQuery("FROM PersonalDocument pd WHERE pd.employee.id =:id");
        getPersonalDocumentListById.setParameter("id", employeeId);
        List<PersonalDocument> personalDocumentList = (List<PersonalDocument>)getPersonalDocumentListById.getResultList();

        return personalDocumentList;
    }


    public List<VisaStatus> test(){
        Session session = getCurrentSession();
        Query getVisaStatus = session.createQuery("FROM VisaStatus");
        return (List<VisaStatus>)getVisaStatus.getResultList();
    }
}
