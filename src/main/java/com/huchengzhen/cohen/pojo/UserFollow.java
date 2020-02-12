package com.huchengzhen.cohen.pojo;

import io.swagger.annotations.ApiParam;

public class UserFollow {
    @ApiParam(hidden = true)
    private Integer id;
    private Integer follower;
    private Integer followed;
}
