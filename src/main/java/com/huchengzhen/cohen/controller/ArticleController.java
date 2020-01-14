package com.huchengzhen.cohen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import com.huchengzhen.cohen.pojo.User;
import com.huchengzhen.cohen.service.ArticleService;
import com.huchengzhen.cohen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private ArticleService articleService;
    private CommentService commentService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
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

    @GetMapping("/all")
    public PageInfo<Article> queryAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "20") int size) {
        PageHelper.startPage(page, size);
        List<Article> articles = articleService.queryAll();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return pageInfo;
    }

    @PostMapping
    public ResponseEntity<String> insertArticle(@RequestBody Article article, Authentication authentication) {
        article.setUploadedDate(new Date());
        article.setEditDate(new Date());
        article.setAuthorId(((User) authentication.getPrincipal()).getId());
        int row = articleService.insertArticle(article);
        return row == 1 ? new ResponseEntity<>("Success", HttpStatus.CREATED) : new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || principal.id == @articleService.findArticleById(#id).authorId")
    @Transactional
    public void deleteArticleById(@PathVariable Integer id) {
        int commentRows = commentService.deleteCommentByArticleId(id);
        int articleRows = articleService.deleteArticleById(id);
    }
}
