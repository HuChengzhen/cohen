package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.Article;
import com.huchengzhen.cohen.pojo.ArticleDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    ArticleDetail findArticleDetailById(Integer id);

    ArticleDetail findArticleDetailByIdJoin(Integer id);

    Article findArticleById(Integer id);
}
