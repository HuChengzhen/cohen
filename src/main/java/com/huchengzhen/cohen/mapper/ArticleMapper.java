package com.huchengzhen.cohen.mapper;

import com.huchengzhen.cohen.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    Article findArticleById(Integer id);

    Article findArticleById2(Integer id);
}
