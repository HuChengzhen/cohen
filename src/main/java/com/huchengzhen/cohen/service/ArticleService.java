package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.mapper.ArticleMapper;
import com.huchengzhen.cohen.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    private void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public Article findArticleById(Integer id) {
        return articleMapper.findArticleById(id);
    }

    public Article findArticleByIdJoin(Integer id) {
        return articleMapper.findArticleByIdJoin(id);
    }
}
