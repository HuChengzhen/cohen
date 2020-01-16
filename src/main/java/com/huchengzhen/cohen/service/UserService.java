package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.mapper.UserMapper;
import com.huchengzhen.cohen.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper
                .loadUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
        return user;
    }

    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @PreAuthorize("principal.id == #id")
    public int updateLastLoginDate(Integer id, Date date) {
        return userMapper.updateLastLoginDate(id, date);
    }
}
