package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.Comment;
import com.huchengzhen.cohen.pojo.User;
import com.huchengzhen.cohen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@Validated
public class CommentController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> findCommentsByArticleId(@NotNull @RequestParam(value = "articleId", required = true) Integer articleId) {
        return commentService.findCommentsByArticleId(articleId);
    }

    @PostMapping
    public ResponseEntity<String> insertComment(@RequestBody Comment comment, Authentication authentication) {
        if (comment.getComment() == null) {
            throw new IllegalArgumentException("Comment not found");
        }

        comment.setComment(comment.getComment().trim());
        if (comment.getComment().isEmpty()) {
            throw new IllegalArgumentException("Comment is empty");
        }

        if (comment.getArticleId() == null) {
            throw new IllegalArgumentException("ArticleId not found");
        }

        User user = (User) authentication.getPrincipal();
        comment.setUserId(user.getId());
        comment.setCommentDate(new Date());
        int row = commentService.insertComment(comment);
        return row == 1 ? new ResponseEntity<>("Success", HttpStatus.CREATED) : new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }
}
