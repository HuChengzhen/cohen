package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserById(Integer id);

    int insertUser(User user);

    User findUserByUsername(String username);
}
