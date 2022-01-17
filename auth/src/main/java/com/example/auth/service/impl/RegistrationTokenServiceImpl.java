package com.example.auth.service.impl;

import com.example.auth.service.RegistrationTokenService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RegistrationTokenServiceImpl implements RegistrationTokenService {

    @Override
    public String getTokenByToken(String token) {
        return null;
    }
}
