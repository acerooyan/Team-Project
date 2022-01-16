package com.example.auth.dao;


import com.example.auth.entity.User;

public interface IUserDao {
    User getUser(String username, String password);
    User merge(User user);
    User getUserById(int id);
}
