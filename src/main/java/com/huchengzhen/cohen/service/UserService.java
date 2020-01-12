package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.mapper.UserMapper;
import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

}
