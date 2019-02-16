package com.zbin.cisp.service.impl;

import com.zbin.cisp.dao.ArticleMapper;
import com.zbin.cisp.domain.Article;
import com.zbin.cisp.service.ArticleService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by Zbin on 2019-02-16
 */
@Service
public class ArticleServiceImpl implements ArticleService {

  @Resource
  ArticleMapper articleMapper;

  @Override
  public void create(Article article) {
    articleMapper.insert(article);
  }
}
