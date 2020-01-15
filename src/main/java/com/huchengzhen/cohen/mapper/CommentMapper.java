package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> findCommentsByArticleId(Integer articleId);

    int deleteCommentByUserId(Integer userId);

    int deleteCommentByArticleId(Integer articleId);

    int insertComment(Comment comment);

    List<Comment> queryAll();

    Comment findCommentById(Integer id);
}
