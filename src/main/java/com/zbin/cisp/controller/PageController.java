package com.zbin.cisp.controller;

import com.zbin.cisp.domain.Category;
import com.zbin.cisp.service.CategoryService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Zbin on 2019-02-13
 */
@Controller
public class PageController {

  @Resource
  CategoryService categoryService;

  @RequestMapping("/")
  public String test() {
    return "index";
  }

  @RequestMapping("/index")
  public String index() {
    return "index";
  }

  @RequestMapping("/login")
  public String login() {
    return "frontend/login";
  }

  @RequestMapping("/register")
  public String trys() {
    return "frontend/register";
  }

  @RequestMapping("/403")
  public String error403() {
    return "403";
  }

  @RequestMapping("/404")
  public String error404() {
    return "404";
  }

  @RequestMapping("/500")
  public String error500() {
    return "500";
  }

  @RequestMapping("/admin")
  public String admin() {
    return "backend/login";
  }

  @RequestMapping("/add")
  public String add(HttpServletRequest request) {
    List<Category> list = categoryService.getAllCategory();
    request.getSession().setAttribute("category", list);
    return "frontend/add";
  }
}
