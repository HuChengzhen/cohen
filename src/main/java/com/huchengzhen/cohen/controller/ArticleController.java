package com.huchengzhen.cohen.controller;

import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public Article findArticleById(@PathVariable Integer id,@RequestParam Boolean needAuthor) {
        Article article = articleService.findArticleById(id);
        if (needAuthor) {
            article.getAuthor();
        }
        return article;
    }
}
