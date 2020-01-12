package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import com.huchengzhen.cohen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/detail/{id}")
    public ArticleDetail findArticleDetailById(@PathVariable Integer id) {
        ArticleDetail articleDetail = articleService.findArticleDetailById(id);
        return articleDetail;
    }

    @GetMapping("/{id}")
    public Article findArticleById(@PathVariable Integer id) {
        return articleService.findArticleById(id);
    }
}
