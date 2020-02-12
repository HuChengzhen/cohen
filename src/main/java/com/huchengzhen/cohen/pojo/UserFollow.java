package com.huchengzhen.cohen.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserFollow {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private Integer follower;
    private Integer followed;
    private Date date;
}
