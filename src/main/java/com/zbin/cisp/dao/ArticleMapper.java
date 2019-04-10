package com.zbin.cisp.dao;

import com.zbin.cisp.domain.Article;
import com.zbin.cisp.vo.ArticleVO;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Zbin on 2019-02-16
 */
@Repository
@Mapper
public interface ArticleMapper {

  void insert(Article article);

  List<ArticleVO> getIndexArticles(@Param("page") Integer page);

  int countIndexArticles();

  ArticleVO getArticleById(Integer id);

  List<ArticleVO> getAticlesByUserId(Integer userId);

  int countAllArticle();

  List<ArticleVO> getArticleByCategoryId(Integer categoryId, Integer page);

  int countArticleByCateId();

  void updateById(Article article);

  void deleteById(Integer id);

  void updateTopStatus(Integer id, String top);

  List<ArticleVO> getTopArticle();

  List<ArticleVO> searchArticle(Integer categoryId, String startTime, String endTime,
    String keyword);

  List<Article> getHotArticles(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

  void addComment(Integer id);

  void deleteComment(Integer id);
}
