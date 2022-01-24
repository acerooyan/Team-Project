package com.example.auth.service.impl;

import com.example.auth.dao.IUserDao;
import com.example.auth.domain.UserDomain;
import com.example.auth.entity.Role;
import com.example.auth.entity.User;
import com.example.auth.entity.UserRole;
import com.example.auth.service.RoleService;
import com.example.auth.service.UserRoleService;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDao userDao;
    private RoleService roleService;
    private UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(RoleService roleService, UserRoleService userRoleService) {
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDomain addUser(UserDomain userDomain) {
        User user = User.builder().userName(userDomain.getUserName()).password(userDomain.getPassword()).email(userDomain.getEmail()).createDate(new Date()).modificationDate(new Date()).build();
        user = userDao.merge(user);
        Role role = roleService.getRoleByName("employee");
        if (role != null) {
            UserRole userRole = UserRole.builder().role(role).user(user).createDate(new Date()).modificationDate(new Date()).activeFlag(1).lastModificationUser(user.getID()).build();
            userRole = userRoleService.addUserRole(userRole);
            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(userRole);
            return UserDomain.builder().userName(user.getUserName()).email(user.getEmail()).build();
        }
        return null;
    }

    public List<UserDomain> checkLogin(UserDomain userDomain) {
        if (userDomain.getUserName().indexOf("@") >= 0) {
            userDomain.setEmail(userDomain.getUserName());
            userDomain.setUserName(null);
        }
        List<UserDomain> res = new ArrayList<>();
        try {
            User user = userDao.getUser(userDomain);
            List<String> roleNames = new ArrayList<>();
            for (UserRole userRole : user.getUserRoleList()) {
                roleNames.add(userRole.getRole().getRoleName());
            }
            userDomain = UserDomain.builder().userName(user.getUserName()).role(roleNames.get(0)).id(user.getID()).email(user.getEmail()).build();
            res.add(userDomain);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
