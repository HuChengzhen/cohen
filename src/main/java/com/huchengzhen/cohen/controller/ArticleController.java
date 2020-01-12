package com.huchengzhen.cohen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import com.huchengzhen.cohen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public PageInfo<Article> queryAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "20") int size) {
        PageHelper.startPage(page, size);
        List<Article> articles = articleService.queryAll();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return pageInfo;
    }
}
