package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.User;
import com.huchengzhen.cohen.pojo.UserDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    User findUserById(Integer id);

    int insertUser(User user);

    Optional<User> loadUserByUsername(String username);

    int deleteUserById(Integer id);

    int updateLastLoginDate(Integer id, Date date);

    List<User> queryAll();

    UserDetail findUserDetailById(Integer id);
}
