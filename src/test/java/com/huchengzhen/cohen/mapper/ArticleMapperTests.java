package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import com.huchengzhen.cohen.pojo.Comment;
import com.huchengzhen.cohen.pojo.User;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ArticleMapperTests {

//    ArticleDetail findArticleDetailById(Integer id);
//
//    ArticleDetail findArticleDetailByIdJoin(Integer id);
//
//    Article findArticleById(Integer id);
//
//    List<Article> queryAll();
//
//    int deleteArticlesByAuthorId(Integer authorId);
//
//    int insertArticle(Article article);
//
//    int deleteArticleById(Integer id);

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    @Transactional
    public void findArticleDetailByIdTests() {
        User user = getUser();
        userMapper.insertUser(user);
        Integer id = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf")).getId();

        Article article = new Article();
        article.setAuthorId(id);
        article.setTitle("asdfas");
        article.setArticle("wqofeja");
        article.setUploadedDate(new Date());
        article.setEditDate(new Date());
        articleMapper.insertArticle(article);
        List<Article> articles = articleMapper.queryAll();
        Integer articleId = articles.get(articles.size() - 1).getId();
        Comment comment = new Comment();
        comment.setUserId(id);
        comment.setArticleId(articleId);
        comment.setComment("asiofdjaosdf");
        comment.setCommentDate(new Date());

        commentMapper.insertComment(comment);
        ArticleDetail articleDetail = articleMapper.findArticleDetailById(articleId);

        assertThat(articleDetail.getId()).isEqualTo(articleId);
        assertThat(articleDetail.getTitle()).isEqualTo(article.getTitle());
        assertThat(articleDetail.getArticle()).isEqualTo(article.getArticle());
        assertThat(articleDetail.getUploadedDate()).isCloseTo(article.getUploadedDate(), 1000);
        assertThat(articleDetail.getEditDate()).isCloseTo(article.getEditDate(), 1000);
        assertThat(articleDetail.getAuthor().getId()).isEqualTo(id);
        assertThat(articleDetail.getComments().size()).isEqualTo(1);
        System.out.println(articleDetail);
    }

    @Test
    @Transactional
    public void findArticleDetailByIdJoinTests() {
        User user = getUser();
        userMapper.insertUser(user);
        Integer id = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf")).getId();

        Article article = new Article();
        article.setAuthorId(id);
        article.setTitle("asdfas");
        article.setArticle("wqofeja");
        article.setUploadedDate(new Date());
        article.setEditDate(new Date());
        articleMapper.insertArticle(article);
        List<Article> articles = articleMapper.queryAll();
        Integer articleId = articles.get(articles.size() - 1).getId();
        Comment comment = new Comment();
        comment.setUserId(id);
        comment.setArticleId(articleId);
        comment.setComment("asiofdjaosdf");
        comment.setCommentDate(new Date());

        commentMapper.insertComment(comment);
        ArticleDetail articleDetail = articleMapper.findArticleDetailByIdJoin(articleId);

        assertThat(articleDetail.getId()).isEqualTo(articleId);
        assertThat(articleDetail.getTitle()).isEqualTo(article.getTitle());
        assertThat(articleDetail.getArticle()).isEqualTo(article.getArticle());
        assertThat(articleDetail.getUploadedDate()).isCloseTo(article.getUploadedDate(), 1000);
        assertThat(articleDetail.getEditDate()).isCloseTo(article.getEditDate(), 1000);
        assertThat(articleDetail.getAuthor().getId()).isEqualTo(id);
        assertThat(articleDetail.getComments().size()).isEqualTo(1);
        System.out.println(articleDetail);
    }

    @Test
    @Transactional
    public void findArticleByIdTests() {
        User user = getUser();
        userMapper.insertUser(user);
        Integer id = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf")).getId();

        Article article = new Article();
        article.setAuthorId(id);
        article.setTitle("asdfas");
        article.setArticle("wqofeja");
        article.setUploadedDate(new Date());
        article.setEditDate(new Date());
        articleMapper.insertArticle(article);
        List<Article> articles = articleMapper.queryAll();
        Integer articleId = articles.get(articles.size() - 1).getId();

        Article article1 = articleMapper.findArticleById(articleId);

        assertThat(article1.getId()).isEqualTo(articleId);
        assertThat(article1.getTitle()).isEqualTo(article.getTitle());
        assertThat(article1.getArticle()).isEqualTo(article.getArticle());
        assertThat(article1.getUploadedDate()).isCloseTo(article.getUploadedDate(), 1000);
        assertThat(article1.getEditDate()).isCloseTo(article.getEditDate(), 1000);
        assertThat(article1.getAuthorId()).isEqualTo(id);
    }

    @Test
    @Transactional
    public void deleteArticlesByAuthorIdTests() {
        User user = getUser();
        userMapper.insertUser(user);
        Integer id = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf")).getId();

        Article article = new Article();
        article.setAuthorId(id);
        article.setTitle("asdfas");
        article.setArticle("wqofeja");
        article.setUploadedDate(new Date());
        article.setEditDate(new Date());
        articleMapper.insertArticle(article);
        List<Article> articles = articleMapper.queryAll();
        Integer articleId = articles.get(articles.size() - 1).getId();

        articleMapper.deleteArticlesByAuthorId(id);

        assertThat(articleMapper.findArticleById(articleId)).isNull();
    }

    @Test
    @Transactional
    public void deleteArticleByIdTests() {
        User user = getUser();
        userMapper.insertUser(user);
        Integer id = userMapper.loadUserByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("asdf")).getId();

        Article article = new Article();
        article.setAuthorId(id);
        article.setTitle("asdfas");
        article.setArticle("wqofeja");
        article.setUploadedDate(new Date());
        article.setEditDate(new Date());
        articleMapper.insertArticle(article);
        List<Article> articles = articleMapper.queryAll();
        Integer articleId = articles.get(articles.size() - 1).getId();

        articleMapper.deleteArticleById(articleId);

        assertThat(articleMapper.findArticleById(articleId)).isNull();
    }

    @NotNull
    private User getUser() {
        String username = "asuidfh";
        String email = "asdfasdfasdf";
        String role = "ROLE_fasdf";
        String password = "wqerasf";
        Date createdDate = new Date();
        Date lastLoginDate = new Date();


        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(role);
        user.setCreateDate(createdDate);
        user.setLastLoginDate(lastLoginDate);
        return user;
    }

}
