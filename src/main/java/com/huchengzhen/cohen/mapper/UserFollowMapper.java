package com.huchengzhen.cohen.mapper;

import java.util.List;

public interface UserFollowMapper {
    List<Integer> findFansByUserId(Integer userId);

    List<Integer> findFollowsByUserId(Integer userId);
}
