package com.example.auth.service.impl;

import com.example.auth.dao.IUserRoleDao;
import com.example.auth.entity.UserRole;
import com.example.auth.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserRoleServiceImpl implements UserRoleService {
    private IUserRoleDao iUserRoleDao;

    @Autowired
    public UserRoleServiceImpl(IUserRoleDao iUserRoleDao) {
        this.iUserRoleDao = iUserRoleDao;
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return iUserRoleDao.addUserRole(userRole);
    }
}
