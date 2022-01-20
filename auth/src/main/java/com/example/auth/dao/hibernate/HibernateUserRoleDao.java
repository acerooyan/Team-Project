package com.example.auth.dao.hibernate;

import com.example.auth.dao.AbstractHbDao;
import com.example.auth.dao.IUserRoleDao;
import com.example.auth.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserRoleDao extends AbstractHbDao<UserRole> implements IUserRoleDao {
    public HibernateUserRoleDao() {
        super.setClazz(UserRole.class);
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return super.merge(userRole);
    }
}
