package com.zbin.cisp.service;

import com.zbin.cisp.domain.Category;
import java.util.List;

/**
 * Created by Zbin on 2019-02-16
 */
public interface CategoryService {

  List<Category> getAllCategory();

  void create(Category category);
}
