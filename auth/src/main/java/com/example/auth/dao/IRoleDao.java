package com.example.auth.dao;

import com.example.auth.entity.Role;

public interface IRoleDao {
    Role getRoleByName(String name);
}
