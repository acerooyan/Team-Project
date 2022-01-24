package com.example.auth.service;

import com.example.auth.entity.RegistrationToken;

public interface RegistrationTokenService {
    RegistrationToken getTokenByTokenAndEmail(String email, String token);

    RegistrationToken addRegistrationToken(RegistrationToken registrationToken);

}
