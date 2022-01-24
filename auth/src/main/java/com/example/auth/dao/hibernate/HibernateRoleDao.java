package com.example.auth.dao.hibernate;

import com.example.auth.dao.AbstractHbDao;
import com.example.auth.dao.IRoleDao;
import com.example.auth.entity.Role;
import com.example.auth.entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateRoleDao extends AbstractHbDao<Role> implements IRoleDao {
    public HibernateRoleDao() {
        super.setClazz(Role.class);
    }

    @Override
    public Role getRoleByName(String name) {
        Session session = super.getCurrentSession();
        DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
        criteria.add(Restrictions.eq("roleName", name));
        List<Role> roles = criteria.getExecutableCriteria(session).list();
        if (roles != null && roles.size() > 0)
            return roles.get(0);
        return null;
    }
}
