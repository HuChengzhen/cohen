package com.huchengzhen.cohen.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Article {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private String title;
    private String article;
    @ApiModelProperty(hidden = true)
    private Integer authorId;
    @ApiModelProperty(hidden = true)
    private Date uploadedDate;
    @ApiModelProperty(hidden = true)
    private Date editDate;
}
