package com.example.emrestserver.service.email;

import com.example.emrestserver.entity.Mail;

public interface EmailService {
    void sendEmail(Mail mail);
}
