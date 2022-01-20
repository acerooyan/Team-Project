package com.example.auth.controller;

import com.example.auth.domain.RegistrationTokenDomain;
import com.example.auth.entity.Mail;
import com.example.auth.entity.RegistrationToken;
import com.example.auth.security.JwtUtil;
import com.example.auth.service.EmailService;
import com.example.auth.service.RegistrationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationTokenController {
    @Autowired
    private RegistrationTokenService registrationTokenService;
    private EmailService emailService;

    @Autowired
    public RegistrationTokenController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/createToken")
    public ResponseEntity<String> createTokenAndSend(HttpServletRequest req, @RequestBody RegistrationTokenDomain registrationTokenDomain) {
        if (registrationTokenDomain != null && registrationTokenDomain.getEmail() != null) {
            String userId = null;
            //TODO: add user id when main service is up.
//            String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
//            Claims claims = JwtUtil.getClaimsFromJwt(token);
//            userId = claims.get("id").toString();
            String jwt = JwtUtil.generateToken(registrationTokenDomain.getEmail(), registrationTokenDomain.getDuration(), null, 0);//duration is minutes
            //TODO: when main service is up, change createdby to userID.
            RegistrationToken registrationToken = registrationTokenService.addRegistrationToken(RegistrationToken.builder().token(jwt).email(registrationTokenDomain.getEmail()).validDuration(registrationTokenDomain.getDuration()).createdBy("1").build());
            if (registrationToken != null) {
                Mail mail = new Mail();
                mail.setMailTo(registrationTokenDomain.getEmail());
                mail.setContentType("text/html");
                mail.setMailSubject("");
                //TODO: url should be front end url not backend url
                mail.setMailContent("This is your registration link: \nhttp://localhost:9999/auth/new?registrationToken=" + jwt);
                emailService.sendEmail(mail);
                return ResponseEntity.ok("Success!");
            }
        }
        return ResponseEntity.ok("Failed!");
    }
}
