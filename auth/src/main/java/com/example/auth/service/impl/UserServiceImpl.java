package com.example.auth.service.impl;

import com.example.auth.dao.IUserDao;
import com.example.auth.domain.UserDomain;
import com.example.auth.entity.User;
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

    public List<UserDomain> checkLogin(String username, String password) {
        List<UserDomain> res = new ArrayList<>();
        try {
            User user = userDao.getUser(username, password);
            UserDomain userDomain = UserDomain.builder().userName(user.getUserName()).id(user.getID()).build();
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
