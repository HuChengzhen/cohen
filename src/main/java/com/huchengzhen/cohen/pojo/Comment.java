package com.huchengzhen.cohen.pojo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    @ApiParam(hidden = true)
    private Integer id;
    @ApiParam(hidden = true)
    private Integer userId;
    private Integer articleId;
    private String comment;
    @ApiParam(hidden = true)
    private Date commentDate;
    @ApiParam(hidden = true)
    private User user;
}
