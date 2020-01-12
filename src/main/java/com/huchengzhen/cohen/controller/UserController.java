package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.auth.api.UserAuthenticationService;
import com.huchengzhen.cohen.pojo.User;
import com.huchengzhen.cohen.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;

@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class UserController {

    private UserService userService;

    @NonNull
    UserAuthenticationService authentication;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Integer id) {
        System.out.println(id);
        return userService.findUserById(id);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setCreateDate(new Date());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int insert = userService.insertUser(user);
        return String.valueOf(user.getId());
    }

    @PostMapping("/login")
    public Integer login(@RequestBody User user) {
        user.setLastLoginDate(new Date());
        return authentication
                .login(user)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}
