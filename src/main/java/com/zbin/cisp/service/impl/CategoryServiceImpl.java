package com.zbin.cisp.service.impl;

import com.zbin.cisp.dao.CategoryMapper;
import com.zbin.cisp.domain.Category;
import com.zbin.cisp.service.CategoryService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by Zbin on 2019-02-16
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  @Resource
  CategoryMapper categoryMapper;

  @Override
  public List<Category> getAllCategory() {
    return categoryMapper.getAllCategory();
  }
}
