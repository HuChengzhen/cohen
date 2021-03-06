package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.mapper.ArticleMapper;
import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    private void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public ArticleDetail findArticleDetailById(Integer id) {
        return articleMapper.findArticleDetailById(id);
    }

    public ArticleDetail findArticleDetailByIdJoin(Integer id) {
        return articleMapper.findArticleDetailByIdJoin(id);
    }

    public Article findArticleById(Integer id) {
        return articleMapper.findArticleById(id);
    }


    public List<Article> queryAll() {
        return articleMapper.queryAll();
    }

    public int deleteArticleByAuthorId(Integer authorId) {
        return articleMapper.deleteArticlesByAuthorId(authorId);
    }

    public int insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    public int deleteArticleById(Integer id) {
        return articleMapper.deleteArticleById(id);
    }
}
