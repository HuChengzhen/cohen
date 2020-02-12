package com.huchengzhen.cohen.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    @ApiModelProperty(hidden = true)
    private Integer id;
    @ApiModelProperty(hidden = true)
    private Integer userId;
    private Integer articleId;
    private String comment;
    @ApiModelProperty(hidden = true)
    private Date commentDate;
    @ApiModelProperty(hidden = true)
    private User user;
}
