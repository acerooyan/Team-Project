package com.example.auth.dao;


public interface IUserDao {
    User getUser(String username, String password);
    User merge(User user);
    User getUserById(int id);
}
