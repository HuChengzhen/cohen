package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.Follow;
import com.huchengzhen.cohen.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follow")
public class UserFollowController {

    private UserFollowService userFollowService;

    @Autowired
    public void setUserFollowService(UserFollowService userFollowService) {
        this.userFollowService = userFollowService;
    }

    @PostMapping
    @PreAuthorize("#follow.follower == principal.id")
    public int follow(@RequestBody Follow follow) throws Exception {
        return userFollowService.follow(follow.getFollower(), follow.getFollowed());
    }

    @DeleteMapping
    @PreAuthorize("#follow.follower == principal.id")
    public int cancel(@RequestBody Follow follow) throws Exception {
        return userFollowService.cancel(follow.getFollower(), follow.getFollowed());
    }
}
