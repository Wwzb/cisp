package com.zbin.cisp.service.impl;

import com.zbin.cisp.dao.ArticleMapper;
import com.zbin.cisp.dao.CommentMapper;
import com.zbin.cisp.domain.Article;
import com.zbin.cisp.service.ArticleService;
import com.zbin.cisp.vo.ArticleVO;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by Zbin on 2019-02-16
 */
@Service
public class ArticleServiceImpl implements ArticleService {

  @Resource
  ArticleMapper articleMapper;

  @Resource
  CommentMapper commentMapper;

  @Override
  public void create(Article article) {
    articleMapper.insert(article);
  }

  @Override
  public List<ArticleVO> getIndexArticles(Integer page) {
    return articleMapper.getIndexArticles(page);
  }

  @Override
  public ArticleVO getArticleById(Integer id) {
    return articleMapper.getArticleById(id);
  }

  @Override
  public List<ArticleVO> getArticlesByUserId(Integer userId) {
    return articleMapper.getAticlesByUserId(userId);
  }

  @Override
  public int countAllArticle() {
    return articleMapper.countAllArticle();
  }

  @Override
  public List<ArticleVO> getArticlesByCategoryId(Integer category, Integer page) {
    return articleMapper.getArticleByCategoryId(category, page);
  }

  @Override
  public void update(Article article) {
    articleMapper.updateById(article);
  }

  @Override
  public void delete(Integer id) {
    articleMapper.deleteById(id);
    commentMapper.deleteByArticleId(id);
  }

  @Override
  public void setTopStatus(Integer id) {
    Article article = articleMapper.getArticleById(id);
    if (article.getTop()) {
      articleMapper.updateTopStatus(id, "false");
    } else {
      articleMapper.updateTopStatus(id, "true");
    }
  }

  @Override
  public List<ArticleVO> getTopArticle() {
    return articleMapper.getTopArticle();
  }

  @Override
  public List<ArticleVO> searchArticle(Integer categoryId, String startTime, String endTime,
    String keyword) {
    return articleMapper.searchArticle(categoryId, startTime, endTime, keyword);
  }

  @Override
  public int countIndexArticles() {
    return articleMapper.countIndexArticles();
  }

  @Override
  public int countArticleByCateId() {
    return articleMapper.countArticleByCateId();
  }

  @Override
  public List<Article> getHotArticles() {
    Date endDate = new Date();
    Date startDate = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
    return articleMapper.getHotArticles(startDate, endDate);
  }
}
