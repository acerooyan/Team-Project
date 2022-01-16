package com.example.auth.service;

import com.example.auth.domain.UserDomain;
import com.example.auth.entity.User;

import java.util.List;

public interface UserService {
    UserDomain addUser(UserDomain userDomain);
    List<UserDomain> checkLogin(String username, String password);
    User getUserById(int id);
}
