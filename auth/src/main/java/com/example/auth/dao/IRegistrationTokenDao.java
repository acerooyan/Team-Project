package com.example.auth.dao;

import com.example.auth.entity.RegistrationToken;

public interface IRegistrationTokenDao {
    RegistrationToken getTokenByToken(String token);
}