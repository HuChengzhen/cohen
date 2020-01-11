package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.pojo.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTests {
    @Autowired
    private ArticleService articleService;

    @Test
    public void findArticleByIdTests() {
        Article article = articleService.findArticleById(1);
        System.out.println(article);
    }

    @Test
    public void findArticleByIdTests2() {
        Article article = articleService.findArticleById2(1);
        System.out.println(article.getArticle());
        System.out.println(article.getAuthor());
    }
}
