package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.Comment;
import com.huchengzhen.cohen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Validated
public class CommentController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> findCommentsByArticleId(@NotNull @RequestParam(value = "articleId",required = true) Integer articleId) {
        return commentService.findCommentsByArticleId(articleId);
    }
}
