package com.huchengzhen.cohen.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserFollowMapperTests {

    @Autowired
    private UserFollowMapper userFollowMapper;

    @Test
    @Transactional
    public void followTests() {
        userFollowMapper.follow(9, 23);
        assertThat(userFollowMapper.findFanIdsByUserId(23)).contains(9);
        assertThat(userFollowMapper.findFollowIdsByUserId(9)).contains(23);
    }

    @Test
    public void cancelTests() {
        userFollowMapper.follow(9, 23);
        assertThat(userFollowMapper.findFanIdsByUserId(23)).contains(9);
        assertThat(userFollowMapper.findFollowIdsByUserId(9)).contains(23);
        userFollowMapper.cancel(9, 23);
        assertThat(userFollowMapper.findFanIdsByUserId(23)).doesNotContain(9);
        assertThat(userFollowMapper.findFollowIdsByUserId(9)).doesNotContain(23);
    }

    @Test
    public void findFanIdsByUserIdTests() {
        userFollowMapper.follow(9, 23);
        userFollowMapper.follow(9, 22);
        assertThat(userFollowMapper.findFollowIdsByUserId(9)).contains(22, 23);
        assertThat(userFollowMapper.findFanIdsByUserId(23)).contains(9);
        assertThat(userFollowMapper.findFanIdsByUserId(22)).contains(9);

        userFollowMapper.cancel(9, 23);
        userFollowMapper.cancel(9, 22);
        assertThat(userFollowMapper.findFollowIdsByUserId(9)).doesNotContain(22, 23);
        assertThat(userFollowMapper.findFanIdsByUserId(23)).doesNotContain(9);
        assertThat(userFollowMapper.findFanIdsByUserId(22)).doesNotContain(9);
    }

    @Test
    public void findFollowerIdsByUserIdTests() {
        userFollowMapper.follow(9, 23);
        userFollowMapper.follow(9, 22);
        assertThat(userFollowMapper.findFollowIdsByUserId(9)).contains(22, 23);
        assertThat(userFollowMapper.findFanIdsByUserId(23)).contains(9);
        assertThat(userFollowMapper.findFanIdsByUserId(22)).contains(9);

        userFollowMapper.cancel(9, 23);
        userFollowMapper.cancel(9, 22);
        assertThat(userFollowMapper.findFollowIdsByUserId(9)).doesNotContain(22, 23);
        assertThat(userFollowMapper.findFanIdsByUserId(23)).doesNotContain(9);
        assertThat(userFollowMapper.findFanIdsByUserId(22)).doesNotContain(9);
    }
}
