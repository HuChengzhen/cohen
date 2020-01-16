package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.Article;
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
public class CommentMapperTests {
    //    List<Comment> findCommentsByArticleId(Integer articleId);
//
//    int deleteCommentByUserId(Integer userId);
//
//    int deleteCommentByArticleId(Integer articleId);
//
//    int insertComment(Comment comment);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    @Transactional
    public void findCommentsByArticleIdTests() {
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
        Comment comment2 = commentMapper.findCommentsByArticleId(articleId).get(0);

        assertCommentWithoutId(comment, comment2);
    }

    @Test
    @Transactional
    public void deleteCommentByUserIdTests() {
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
        commentMapper.deleteCommentByUserId(id);
        assertThat(commentMapper.findCommentsByArticleId(articleId).isEmpty()).isTrue();
    }

    @Test
    @Transactional
    public void deleteCommentByArticleIdTests() {
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
        commentMapper.deleteCommentByArticleId(articleId);
        assertThat(commentMapper.findCommentsByArticleId(articleId).isEmpty()).isTrue();
    }

    @Test
    @Transactional
    public void insertCommentTests() {
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
        Comment comment2 = commentMapper.findCommentsByArticleId(articleId).get(0);

        assertCommentWithoutId(comment, comment2);
    }

    private void assertCommentWithoutId(Comment comment, Comment comment2) {
        assertThat(comment.getArticleId()).isEqualTo(comment2.getArticleId());
        assertThat(comment.getUserId()).isEqualTo(comment2.getUserId());
        assertThat(comment.getComment()).isEqualTo(comment2.getComment());
        assertThat(comment.getCommentDate()).isCloseTo(comment2.getCommentDate(), 1000);
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
