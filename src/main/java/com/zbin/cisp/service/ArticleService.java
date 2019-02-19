package com.zbin.cisp.service;

import com.zbin.cisp.domain.Article;
import com.zbin.cisp.vo.ArticleVO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by Zbin on 2019-02-16
 */
@Service
public interface ArticleService {

  void create(Article article);

  List<ArticleVO> getIndexArticles();

  ArticleVO getArticleById(Integer id);

  List<ArticleVO> getArticlesByUserId(Integer userId);

  int countAllArticle();
}
