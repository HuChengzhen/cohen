package com.huchengzhen.cohen.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer id;
    private String title;
    private String article;
    private Integer authorId;
    private Date uploadedDate;
    private Date editDate;
    private User author;
}
