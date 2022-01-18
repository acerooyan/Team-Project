package com.example.auth.service.impl;

import com.example.auth.dao.IUserDao;
import com.example.auth.domain.UserDomain;
import com.example.auth.entity.User;
import com.example.auth.entity.UserRole;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDao userDao;


    @Override
    public UserDomain addUser(UserDomain userDomain) {
        User user = User.builder().userName(userDomain.getUserName()).password(userDomain.getPassword()).build();
        User newUser = userDao.merge(user);
        if (newUser == null)
            return null;
        return UserDomain.builder().userName(newUser.getUserName()).password(newUser.getPassword()).build();
    }

    public List<UserDomain> checkLogin(UserDomain userDomain) {
        if(userDomain.getUserName().indexOf("@")>=0){
            userDomain.setEmail(userDomain.getUserName());
            userDomain.setUserName(null);
        }
        List<UserDomain> res = new ArrayList<>();
        try {
            User user = userDao.getUser(userDomain);
            List<String> roleNames = new ArrayList<>();
            for(UserRole userRole : user.getUserRole()){
                roleNames.add(userRole.getRole().getRoleName());
            }
            userDomain = UserDomain.builder().userName(user.getUserName()).role(roleNames).id(user.getID()).email(user.getEmail()).build();
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
}
