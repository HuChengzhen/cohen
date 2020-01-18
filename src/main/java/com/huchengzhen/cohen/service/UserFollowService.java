package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.mapper.UserFollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFollowService {
    private UserFollowMapper userFollowMapper;

    @Autowired
    public void setUserFollowMapper(UserFollowMapper userFollowMapper) {
        this.userFollowMapper = userFollowMapper;
    }

    public int follow(Integer follower, Integer followed) {
        return userFollowMapper.follow(follower, followed);
    }

    public int cancel(Integer follower, Integer followed) {
        return userFollowMapper.cancel(follower, followed);
    }
}
