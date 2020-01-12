package com.huchengzhen.cohen.auth.api;

import com.huchengzhen.cohen.pojo.User;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<Integer> login(User user);

    Optional<User> findByToken(Integer token);

    void logout(User user);
}
