package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.mapper.CommentMapper;
import com.huchengzhen.cohen.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentMapper commentMapper;

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<Comment> findCommentsByArticleId(Integer id) {
        return commentMapper.findCommentsByArticleId(id);
    }
}
