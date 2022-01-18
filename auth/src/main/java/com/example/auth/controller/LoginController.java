package com.example.auth.controller;

import com.example.auth.constant.JwtConstant;
import com.example.auth.domain.UserDomain;
import com.example.auth.security.CookieUtil;
import com.example.auth.security.JwtUtil;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse res, @RequestBody UserDomain userDomain) {
        if (userDomain != null && userDomain.getUserName() != null && userDomain.getPassword() != null) {
            List<UserDomain> userDomainList = userService.checkLogin(userDomain);
            if (userDomainList != null && userDomainList.size() > 0) {
                String jwt = JwtUtil.generateToken(userDomain.getEmail(), JwtConstant.JWT_VALID_DURATION, userDomainList.get(0).getRole(), userDomainList.get(0).getId());
                CookieUtil.create(res, JwtConstant.JWT_COOKIE_NAME, jwt, false, -1, "localhost");
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Invalid username or password");
    }

    @GetMapping("/new")
    public String savePerson(Model model, @RequestParam("registrationToken") String registrationToken) {
        model.addAttribute("user", new UserDomain());
        return "registerForm";
    }

//    @PostMapping("/registration")
//    public String addUser(HttpServletRequest req, Model model, @ModelAttribute("user") @Valid UserDomain userDomain,
//                          BindingResult result) {
//        if (result.hasErrors()) {
//            return "registerForm";
//        }
//        UserDomain newUserDomain = userService.addUser(userDomain);
//        if (newUserDomain == null)
//            return "registerForm";
//        return "redirect:" + "/auth/login";
//    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse res, @RequestParam("redirect") String redirect) {
        CookieUtil.clear(res, JwtConstant.JWT_COOKIE_NAME, "localhost");
        return "redirect:" + redirect;
    }

//    @GetMapping("/getUser")
//    @ResponseBody
//    public String getUser(HttpServletResponse res, @RequestParam("id") Integer id) {
//        User user = userService.getUserById(id);
//        return user.getFirstname()+" "+user.getLastname();
//    }
}
