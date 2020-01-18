package com.huchengzhen.cohen.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserFollowMapper {
    List<Integer> findFanIdsByUserId(Integer userId);

    List<Integer> findFollowIdsByUserId(Integer userId);

    int follow(Integer follower, Integer followed);

    int cancel(Integer follower, Integer followed);
}
