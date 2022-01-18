package com.example.auth.dao.hibernate;

import com.example.auth.dao.AbstractHbDao;
import com.example.auth.dao.IUserDao;
import com.example.auth.domain.UserDomain;
import com.example.auth.entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateUserDao extends AbstractHbDao<User> implements IUserDao {
    public HibernateUserDao() {
        super.setClazz(User.class);
    }

    @Override
    public User getUser(UserDomain userDomain) {
        Session session = super.getCurrentSession();
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        if (userDomain != null) {
            if (userDomain.getUserName() != null && !userDomain.getUserName().isEmpty()) {
                criteria.add(Restrictions.eq("userName", userDomain.getUserName()));
            }
            if (userDomain.getEmail() != null && !userDomain.getEmail().isEmpty()) {
                criteria.add(Restrictions.eq("email", userDomain.getEmail()));
            }
            if (userDomain.getPassword() != null && !userDomain.getPassword().isEmpty()) {
                criteria.add(Restrictions.eq("password", userDomain.getPassword()));
            }
            return (User) criteria.getExecutableCriteria(session).uniqueResult();
        }
        return null;
    }


    @Override
    public User merge(User user) {
        Session session = super.getCurrentSession();
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("userName", user.getUserName()));
        List<User> existedUser = criteria.getExecutableCriteria(session).list();
        if (existedUser != null && existedUser.size() > 0) {
            return null;
        }
        return super.merge(user);
    }

    @Override
    public User getUserById(int id) {
        return super.findById(id);
    }

}
