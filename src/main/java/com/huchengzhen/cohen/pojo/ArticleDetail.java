package com.huchengzhen.cohen.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDetail {
    private Integer id;
    private String title;
    private String article;
    private Integer authorId;
    private Date uploadedDate;
    private Date editDate;
    private User author;
    private List<Comment> comments;
}
