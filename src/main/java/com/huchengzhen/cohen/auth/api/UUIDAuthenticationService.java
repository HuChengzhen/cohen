package com.huchengzhen.cohen.auth.api;

import com.huchengzhen.cohen.pojo.User;
import com.huchengzhen.cohen.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class UUIDAuthenticationService implements UserAuthenticationService {
    @NonNull
    UserService userService;

    @Override
    public Optional<Integer> login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        User userInDatabase = userService.findUserByUsername(user.getUsername());
        if (userInDatabase == null) {
            return Optional.empty();
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(user.getPassword(), userInDatabase.getPassword())) {
            return Optional.empty();
        }

        return Optional.of(userInDatabase.getId());
    }

    @Override
    public Optional<User> findByToken(final Integer token) {
        return Optional.of(userService.findUserById(token));
    }

    @Override
    public void logout(final User user) {

    }
}
