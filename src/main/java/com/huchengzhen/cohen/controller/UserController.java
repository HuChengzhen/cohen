package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.User;
import com.huchengzhen.cohen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Integer id) {
        System.out.println(id);
        return userService.findUserById(id);
    }

    @PostMapping
    public ResponseEntity<String> insertUser(@RequestBody User user) {
        user.setCreateDate(new Date());
        int insert = userService.insertUser(user);
        return insert == 1 ?
                new ResponseEntity<>("insert: " + insert, HttpStatus.CREATED)
                :
                new ResponseEntity<>("insert fail", HttpStatus.BAD_REQUEST);
    }
}
