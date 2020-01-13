package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Mapper
public interface UserMapper {
    User findUserById(Integer id);

    int insertUser(User user);

    Optional<User> loadUserByUsername(String username);
}
