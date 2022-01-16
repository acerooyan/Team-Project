package com.example.auth.controller;

import com.example.auth.constant.JwtConstant;
import com.example.auth.domain.UserDomain;
import com.example.auth.entity.User;
import com.example.auth.security.CookieUtil;
import com.example.auth.security.JwtUtil;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("redirect") String redirect, HttpServletResponse res,
                        @RequestParam String username, @RequestParam String password, Model model) {

        List<UserDomain> userDomainList = userService.checkLogin(username, password);
        if (username == null || password == null || userDomainList == null) {
            model.addAttribute("credentialError", "Invalid username or password");
            return "login";
        }

        String jwt = JwtUtil.generateToken(username, JwtConstant.JWT_VALID_DURATION, userDomainList.get(0).getAdmin().toString(), userDomainList.get(0).getId(), userDomainList.get(0).getFirstName(), userDomainList.get(0).getLastName());
        //Setting maxAge to -1 will preserve it until the browser is closed.
        CookieUtil.create(res, JwtConstant.JWT_COOKIE_NAME, jwt, false, -1, "localhost");

        return "redirect:" + redirect;
    }

    @GetMapping("/new")
    public String savePerson(Model model) {
        model.addAttribute("user", new UserDomain());
        return "registerForm";
    }

    @PostMapping("/registration")
    public String addUser(HttpServletRequest req, Model model, @ModelAttribute("user") @Valid UserDomain userDomain,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "registerForm";
        }
        UserDomain newUserDomain = userService.addUser(userDomain);
        if (newUserDomain == null)
            return "registerForm";
        return "redirect:" + "/auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse res, @RequestParam("redirect") String redirect) {
        CookieUtil.clear(res, JwtConstant.JWT_COOKIE_NAME, "localhost");
        return "redirect:" + redirect;
    }

    @GetMapping("/getUser")
    @ResponseBody
    public String getUser(HttpServletResponse res, @RequestParam("id") Integer id) {
        User user = userService.getUserById(id);
        return user.getFirstname()+" "+user.getLastname();
    }
}
