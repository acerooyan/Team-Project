package com.example.emrestserver.dao;

import com.example.emrestserver.entity.Employee;
import com.example.emrestserver.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class employeeDao1 {
    private PersonDao personDao;

    @Autowired
    public employeeDao1(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<Employee> getAllEmployees(Integer curPage, Integer totalNum, Integer maxResult, String email) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
        if (email != null){
            Person person = personDao.getPersonByEmail1(email);
            detachedCriteria.add(Restrictions.eq("person", person));
        }
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        if (curPage == null) curPage = 1;
        if (totalNum == null) {
            Criteria getTotalCriteria = session.createCriteria(Person.class).setProjection(Projections.rowCount());
            Long l = (Long) getTotalCriteria.list().get(0);
            totalNum = l.intValue();
        }
        if(maxResult==null)maxResult = 2;
        criteria.setFirstResult((curPage-1) * maxResult);
        criteria.setMaxResults(maxResult);
        List<Employee> employeeList = criteria.list();
        if (employeeList != null && employeeList.size() > 0) {
            employeeList.get(0).setTotalNum(totalNum);
            employeeList.get(0).setCurPage(curPage);
            employeeList.get(0).setMaxResult(maxResult);
        }
        return employeeList;
    }
}