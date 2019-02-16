package com.zbin.cisp.dao;

import com.zbin.cisp.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Zbin on 2019-02-16
 */
@Repository
@Mapper
public interface ArticleMapper {

  void insert(Article article);
}
