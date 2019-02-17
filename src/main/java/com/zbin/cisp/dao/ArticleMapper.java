package com.zbin.cisp.dao;

import com.zbin.cisp.domain.Article;
import com.zbin.cisp.vo.ArticleVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Zbin on 2019-02-16
 */
@Repository
@Mapper
public interface ArticleMapper {

  void insert(Article article);

  List<ArticleVO> getIndexArticles();

  ArticleVO getArticleById(Integer id);
}
