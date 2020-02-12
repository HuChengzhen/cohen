package com.huchengzhen.cohen.pojo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class Article {
    @ApiParam(hidden = true)
    private Integer id;
    private String title;
    private String article;
    @ApiParam(hidden = true)
    private Integer authorId;
    @ApiParam(hidden = true)
    private Date uploadedDate;
    @ApiParam(hidden = true)
    private Date editDate;
}
