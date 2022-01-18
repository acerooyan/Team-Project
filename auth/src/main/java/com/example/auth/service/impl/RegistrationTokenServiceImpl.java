package com.example.auth.service.impl;

import com.example.auth.dao.IRegistrationTokenDao;
import com.example.auth.entity.RegistrationToken;
import com.example.auth.service.RegistrationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RegistrationTokenServiceImpl implements RegistrationTokenService {
    private IRegistrationTokenDao iRegistrationTokenDao;

    @Autowired
    public RegistrationTokenServiceImpl(IRegistrationTokenDao iRegistrationTokenDao) {
        this.iRegistrationTokenDao = iRegistrationTokenDao;
    }

    @Override
    public RegistrationToken getTokenByTokenAndEmail(String email, String token) {
        return iRegistrationTokenDao.getTokenByTokenAndEmail(email, token);
    }
}
