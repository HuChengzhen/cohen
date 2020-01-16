package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void loadUserByUsernameTests() {
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

        userService.insertUser(user);
        User user2 = (User) userService.loadUserByUsername(user.getUsername());

        assertThat(user).isEqualTo(user2);
        assertThat(user.getUsername()).isEqualTo(user2.getUsername());
        assertThat(user.getEmail()).isEqualTo(user2.getEmail());
        assertThat(user.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user.getRoles()).isEqualTo(user2.getRoles());
        assertThat(user.getCreateDate()).isCloseTo(user2.getCreateDate(), 1000);
        assertThat(user.getLastLoginDate()).isCloseTo(user2.getLastLoginDate(), 1000);
        assertThat(user2.getId()).isGreaterThan(0);
    }

    @Test()
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void updateLastLoginDateThrowsTests() {
        Assertions.assertThrows(AccessDeniedException.class, () -> {
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
            userService.insertUser(user);
            Thread.sleep(1);
            Integer id = ((User) userService.loadUserByUsername(user.getUsername())).getId();
            Date date = new Date();
            userService.updateLastLoginDate(id, date);
        });
    }

    @Test()
    @Transactional
    @WithUserDetails(value = "hcz", userDetailsServiceBeanName = "userService")
    public void updateLastLoginDateTests() {
        Integer id = ((User) userService.loadUserByUsername("hcz")).getId();
        Date date = new Date();
        userService.updateLastLoginDate(id, date);
        User user = userService.findUserById(id);
        assertThat(user.getLastLoginDate()).isCloseTo(date, 1000);
    }
}
