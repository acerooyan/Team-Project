package com.example.emrestserver.dao;

import com.example.emrestserver.entity.*;
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


    public Employee getEmployeeById(Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Employee E WHERE id ="+ id);
        return (Employee) query.getSingleResult();

    }

    public Employee getEmployeeByEmail(String email){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Employee e WHERE e.person.email = :email");
        query.setParameter("email",email);
        Employee employee = (Employee) query.getSingleResult();
        System.out.println(employee.getVisaStatus().getVisaType());
        return employee;
    }


    public Address[] getAddressByPersonId(Integer personId){
        System.out.println(personId);
        Session session = getCurrentSession();
        Query getAddress = session.createQuery("From Address WHERE person.id =:id");
        getAddress.setParameter("id", personId);
        List<Address> addressList = (List<Address>)getAddress.getResultList();
        Address[] addressArray = new Address[addressList.size()];
        addressList.toArray(addressArray);
        return addressArray;
    }

    public Contact getEmergencyByEmployeeId(Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Contact c WHERE c.employee.id = :id and c.isEmergency = 1");
        query.setParameter("id",id);
        Contact contact = (Contact) query.getSingleResult();
        System.out.println(contact.getPerson().getId());
        return contact;
    }

    public PersonalDocument[] getDocumentByEmployeeId(Integer id){
        Session session = getCurrentSession();

        Query query = session.createQuery("FROM  PersonalDocument p WHERE p.employee.id = :id");
        query.setParameter("id",id);
        List<PersonalDocument> personalDocumentList= (List<PersonalDocument>) query.getResultList();

        PersonalDocument[] personalDocuments = new PersonalDocument[personalDocumentList.size()];
        personalDocuments = personalDocumentList.toArray(personalDocuments);

        return personalDocuments;
    }

    public ApplicationWorkFlow getOnboardApplicationWorkFlowById(Integer employeeId){
        Session session = getCurrentSession();
        Query getVisaStatus = session.createQuery("FROM ApplicationWorkFlow awf WHERE employee.id=:id AND type='onBoarding'");
        getVisaStatus.setParameter("id", employeeId);
        return (ApplicationWorkFlow)getVisaStatus.getSingleResult();
    }

    public Employee updateEmployee(Employee employee){
        Session session = getCurrentSession();
        return (Employee) session.merge(employee);
    }

    public ApplicationWorkFlow getLargestWorkFlowByEmail(String email){
        Employee employee = getEmployeeByEmail(email);
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM ApplicationWorkFlow a WHERE employee =:employee");
        query.setParameter("employee", employee);
        List<ApplicationWorkFlow> applicationWorkFlowList = query.getResultList();

        return applicationWorkFlowList.get(applicationWorkFlowList.size()-1);
    }
    
   public VisaStatus updateVisaStatus(String email,String visaType){
       VisaStatus visaStatus = getEmployeeByEmail(email).getVisaStatus();
       visaStatus.setVisaType(visaType);
       Session session = getCurrentSession();
       return (VisaStatus) session.merge(visaStatus);

   }

   public ApplicationWorkFlow updateWorkFlowByType(String type,String email,String comments,String status){
       Employee employee = getEmployeeByEmail(email);
       Session session = getCurrentSession();
       Query query = session.createQuery("FROM ApplicationWorkFlow a WHERE employee =:employee and type = :type");
       query.setParameter("employee", employee);
       query.setParameter("type", type);
       ApplicationWorkFlow applicationWorkFlow = (ApplicationWorkFlow) query.getSingleResult();
       applicationWorkFlow.setComments(comments);;
       applicationWorkFlow.setStatus(status);

       return (ApplicationWorkFlow) session.merge(applicationWorkFlow);
   }



}
