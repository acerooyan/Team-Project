package com.example.auth.service.impl;

import com.example.auth.dao.IRoleDao;
import com.example.auth.entity.Role;
import com.example.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    private IRoleDao iRoleDao;

    @Autowired
    public RoleServiceImpl(IRoleDao iRoleDao) {
        this.iRoleDao = iRoleDao;
    }

    @Override
    public Role getRoleByName(String name) {
        return iRoleDao.getRoleByName(name);
    }
}
