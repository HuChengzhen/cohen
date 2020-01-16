package com.huchengzhen.cohen.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huchengzhen.cohen.pojo.User;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional
    public void updateLastLoginDateTests() {
        User user = getUser();
        userMapper.insertUser(user);
        User user2 = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf"));
        Date date = new Date();
        userMapper.updateLastLoginDate(user2.getId(), date);
        User user1 = userMapper.findUserById(user2.getId());
        assertThat(user1.getLastLoginDate()).isCloseTo(date, 1000);
    }

    @Test
    @Transactional
    public void deleteUserByIdTests() {
        User user = getUser();
        userMapper.insertUser(user);
        User user2 = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf"));
        userMapper.deleteUserById(user2.getId());
        assertThat(userMapper.findUserById(user2.getId())).isNull();
    }

    @Test
    @Transactional
    public void loadUserByUsernameTests() {
        User user = getUser();
        userMapper.insertUser(user);
        User user2 = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf"));
        assertUserWithoutIdAndPassword(user, user2);
        assertThat(user.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user2.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    public void insertUserTests() {
        User user = getUser();
        userMapper.insertUser(user);
        User user2 = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf"));
        assertUserWithoutIdAndPassword(user, user2);
        assertThat(user.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user2.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    public void findUserByIdTests() {
        PageHelper.startPage(0, 10);
        List<User> users = userMapper.queryAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        User user = pageInfo.getList().get(0);

        User user2 = userMapper.findUserById(user.getId());
        assertThat(user.getId()).isEqualTo(user2.getId());
        assertUserWithoutIdAndPassword(user, user2);
    }


    @Test
    @Transactional
    public void queryAllTests() {
        User user = getUser();
        userMapper.insertUser(user);
        PageHelper.startPage(Integer.MAX_VALUE, 10);
        List<User> list = userMapper.queryAll();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        User user2 = pageInfo.getList().get(pageInfo.getList().size() - 1);
        assertUserWithoutIdAndPassword(user, user2);
    }

    @NotNull
    private User getUser() {
        String username = "asuidfh";
        String email = "asdfasdfasdf";
        String role = "ROLE_fasdf";
        String password = "wqerasf";
        Date createdDate = new Date();
        Date lastLoginDate = new Date();


        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(role);
        user.setCreateDate(createdDate);
        user.setLastLoginDate(lastLoginDate);
        return user;
    }

    private void assertUserWithoutIdAndPassword(User user, User user2) {
        assertThat(user).isEqualTo(user2);
        assertThat(user.getUsername()).isEqualTo(user2.getUsername());
        assertThat(user.getEmail()).isEqualTo(user2.getEmail());
        assertThat(user.getRoles()).isEqualTo(user2.getRoles());
        assertThat(user.getCreateDate()).isCloseTo(user2.getCreateDate(), 1000);
        assertThat(user.getLastLoginDate()).isCloseTo(user2.getLastLoginDate(), 1000);
    }


}
