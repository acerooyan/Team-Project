package com.example.emrestserver.dao;

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
public class PersonDao {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Person> getAllPerson(Integer curPage, Integer totalNum, Integer maxResult, String email) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Person.class);
        if (email != null) detachedCriteria.add(Restrictions.eq("email", email));
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        if (curPage == null) curPage = 1;
        if (totalNum == null) {
            Criteria getTotalCriteria = session.createCriteria(Person.class).setProjection(Projections.rowCount());
            Long l = (Long) getTotalCriteria.list().get(0);
            totalNum = l.intValue();
        }
        if(maxResult==null)maxResult = 2;
        criteria.setFirstResult(curPage * maxResult);
        criteria.setMaxResults(maxResult);
        List<Person> personList = criteria.list();
        if (personList != null && personList.size() > 0) {
            personList.get(0).setTotalNum(totalNum);
            personList.get(0).setCurPage(curPage);
            personList.get(0).setMaxResult(maxResult);
        }
        return personList;
    }
}
