package com.huchengzhen.cohen.pojo;

import io.swagger.annotations.ApiParam;

import java.util.Date;

public class UserFollow {
    @ApiParam(hidden = true)
    private Integer id;
    private Integer follower;
    private Integer followed;
    private Date date;
}
