package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    ArticleDetail findArticleDetailById(Integer id);

    ArticleDetail findArticleDetailByIdJoin(Integer id);

    Article findArticleById(Integer id);

    List<Article> queryAll();

    int deleteArticlesByAuthorId(Integer authorId);

    int insertArticle(Article article);
}
