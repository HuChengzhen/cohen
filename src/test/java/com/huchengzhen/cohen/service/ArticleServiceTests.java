package com.huchengzhen.cohen.service;

import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import com.huchengzhen.cohen.pojo.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@SpringBootTest
public class ArticleServiceTests {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Test
    public void findArticleByIdTests() {
        long startTime = System.currentTimeMillis();
        ArticleDetail articleDetail = articleService.findArticleDetailById(1);
        System.out.println(articleDetail);
        long endTime = System.currentTimeMillis();
        System.out.println("findArticleByIdTests Total execution time: " + (endTime-startTime) + "ms");
    }

    @Test
    public void findArticleByIdJoinTests() {
        long startTime = System.currentTimeMillis();
        ArticleDetail articleDetail = articleService.findArticleDetailByIdJoin(1);
        System.out.println(articleDetail);
        long endTime = System.currentTimeMillis();
        System.out.println("findArticleByIdJoinTests Total execution time: " + (endTime-startTime) + "ms");
    }
}
