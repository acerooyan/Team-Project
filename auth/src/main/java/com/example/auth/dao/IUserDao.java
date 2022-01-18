package com.example.auth.dao;


import com.example.auth.domain.UserDomain;
import com.example.auth.entity.User;

public interface IUserDao {
    User getUser(UserDomain userDomain);

    User merge(User user);

    User getUserById(int id);
}
