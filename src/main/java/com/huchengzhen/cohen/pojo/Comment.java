package com.huchengzhen.cohen.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer articleId;
    private String comment;
    private Date commentDate;
}
