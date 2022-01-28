package com.example.auth.service;

//import com.example.auth.Exception.UserNotFoundException;
import com.example.auth.domain.UserDomain;
import com.example.auth.entity.User;

import java.util.List;

public interface UserService {
    UserDomain addUser(UserDomain userDomain);

    List<UserDomain> checkLogin(UserDomain userDomain);

    User getUserById(int id);

    User getUserByEmail(String email);

    User getUserByName(String name);
}
