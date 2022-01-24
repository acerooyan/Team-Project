package com.example.emrestserver.dao;

import com.example.emrestserver.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
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
        try{
            Query findPerson = session.createQuery("FROM Person WHERE email=:email");
            findPerson.setParameter("email", p.getEmail());

            return (Person)findPerson.getSingleResult();

        }catch(NoResultException e){
            Integer personId = (Integer)session.save(p);
            Query findPersonById = session.createQuery("FROM Person p WHERE p.id = :id");
            findPersonById.setParameter("id", personId);
            System.out.println(personId);
            Person p1 = (Person)findPersonById.getSingleResult();
            System.out.println(personId+" == "+ p1.getId() );
            return p;
        }

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

    public Employee getEmployeeById(Integer id){
        Session session = getCurrentSession();
        Query findEmployee = session.createQuery("FROM Employee  where id=:id");
        findEmployee.setParameter("id", id);
        return (Employee) findEmployee.getSingleResult();
    }
    public Employee addEmployee(Employee employee){
        Session session = getCurrentSession();
        Integer employeeId = (Integer) session.save(employee);
        return getEmployeeById(employeeId);
    }

    public Contact getContactById(Integer id){
        Session session = getCurrentSession();
        Query findContact = session.createQuery("FROM Contact WHERE id=:id");
        findContact.setParameter("id", id);
        return (Contact) findContact.getSingleResult();

    }

    public Contact addContact(Contact contact){
        Session session = getCurrentSession();
        Integer contactId = (Integer) session.save(contact);
        return getContactById(contactId);
    }

    public ApplicationWorkFlow getApplicationById(Integer id){
        Session session = getCurrentSession();
        Query findApplication = session.createQuery("FROM ApplicationWorkFlow WHERE id=:id");
        findApplication.setParameter("id", id);
        return (ApplicationWorkFlow) findApplication.getSingleResult();
    }

    public ApplicationWorkFlow addApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow){
        Session session = getCurrentSession();
        Integer applicationId = (Integer) session.save(applicationWorkFlow);
        return getApplicationById(applicationId);
    }


}
