package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.User;
import com.huchengzhen.cohen.service.ArticleService;
import com.huchengzhen.cohen.service.CommentService;
import com.huchengzhen.cohen.service.UserService;
import com.huchengzhen.cohen.utils.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
//TODO delete status code
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private UserService userService;
    private CommentService commentService;
    private ArticleService articleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping("/register")
    public ResponseEntity insertUser(@RequestBody User user) {
        if (user.getUsername() == null) {
            throw new IllegalArgumentException("Username not found");
        }

        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email not found");
        }

        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password not found");
        }

        user.setUsername(user.getUsername().trim());
        user.setEmail(user.getEmail().trim());
        if (!StringMatcher.isValidUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username wrong format");
        }

        if (!StringMatcher.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email wrong format");
        }

        if (!StringMatcher.isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Password wrong format");
        }

        user.setCreateDate(new Date());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        int insert = userService.insertUser(user);
        return new ResponseEntity(insert == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and #id != principal.id")
    @Transactional
    public void deleteUser(@PathVariable("id") Integer id) {
        int commentRows = commentService.deleteCommentByUserId(id);
        int articleRows = articleService.deleteArticleByAuthorId(id);
        int userRows = userService.deleteUserById(id);
    }

    @GetMapping
    public User currentUser(Authentication authentication) throws CloneNotSupportedException {
        User user = (User) ((User) authentication.getPrincipal()).clone();
        user.setPassword(null);
        return user;
    }

    @GetMapping("/name")
    public User loadUserByUsername(@NotNull @RequestParam(value = "username", required = true) String username) {
        return (User) userService.loadUserByUsername(username);
    }
}
