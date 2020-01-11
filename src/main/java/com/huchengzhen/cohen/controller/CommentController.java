package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.Comment;
import com.huchengzhen.cohen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> findCommentsByArticleId(@RequestParam("articleId") Integer id) {
        return commentService.findCommentsByArticleId(id);
    }

}
