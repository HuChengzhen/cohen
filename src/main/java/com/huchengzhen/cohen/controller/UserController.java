package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.auth.api.UserAuthenticationService;
import com.huchengzhen.cohen.pojo.User;
import com.huchengzhen.cohen.service.UserService;
import com.huchengzhen.cohen.util.TokenUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private AuthenticationManager authenticationManager;
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
        String token = TokenUtil.createToken(user);
        return token;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        user.setLastLoginDate(new Date());
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            return new ResponseEntity<>("wrong password or username", HttpStatus.FORBIDDEN);
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(TokenUtil.createToken(userDetails), HttpStatus.OK);
    }
}
