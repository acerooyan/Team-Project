package com.example.auth.controller;

import com.example.auth.constant.JwtConstant;
import com.example.auth.domain.UserDomain;
import com.example.auth.entity.RegistrationToken;
import com.example.auth.entity.User;
import com.example.auth.security.CookieUtil;
import com.example.auth.security.JwtUtil;
import com.example.auth.service.RegistrationTokenService;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class LoginController {
    @Autowired
    private UserService userService;
    private RegistrationTokenService registrationTokenService;

    @Autowired
    public LoginController(RegistrationTokenService registrationTokenService) {
        this.registrationTokenService = registrationTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse res, @RequestBody UserDomain userDomain) {
        System.out.println("in log in");
        if (userDomain != null && userDomain.getUserName() != null && userDomain.getPassword() != null) {
            List<UserDomain> userDomainList = userService.checkLogin(userDomain);
            if (userDomainList != null && userDomainList.size() > 0) {
                if(userDomain.getRole().equals("HR")){
                    if(!userDomainList.get(0).getRole().equals(userDomain.getRole()))return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have this role!");
                }
                String jwt = JwtUtil.generateToken(userDomain.getEmail(), JwtConstant.JWT_VALID_DURATION, userDomainList.get(0).getRole(), userDomainList.get(0).getId());
                CookieUtil.create(res, JwtConstant.JWT_COOKIE_NAME, jwt, false, -1, "localhost");
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username or password");
    }

    @GetMapping("/new")
    public ResponseEntity<String> savePerson(@RequestParam("registrationToken") String registrationToken) {
        String email = JwtUtil.getSubjectFromJwt(registrationToken);
        if (email == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Token");
        if (email.equals("expired")) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Expired Token");
        RegistrationToken registrationToken1 = registrationTokenService.getTokenByTokenAndEmail(email, registrationToken);
        if (registrationToken1 != null) return ResponseEntity.ok(registrationToken1.getEmail());
        return ResponseEntity.ok("Invalid Token");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> addUser(@RequestBody UserDomain userDomain) {
        User existedUser = userService.getUserByName(userDomain.getUserName());
        if (existedUser != null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Existed UserName!");
        existedUser = userService.getUserByEmail(userDomain.getEmail());
        if (existedUser != null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Existed Email Address!");
        UserDomain newUserDomain = userService.addUser(userDomain);
        if (newUserDomain == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failed");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse res) {
        CookieUtil.clear(res, JwtConstant.JWT_COOKIE_NAME, "localhost");
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/getUser")
//    @ResponseBody
//    public String getUser(HttpServletResponse res, @RequestParam("id") Integer id) {
//        User user = userService.getUserById(id);
//        return user.getFirstname()+" "+user.getLastname();
//    }
}
