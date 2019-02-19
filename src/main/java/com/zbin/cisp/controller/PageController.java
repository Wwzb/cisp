package com.zbin.cisp.controller;

import com.zbin.cisp.domain.Article;
import com.zbin.cisp.domain.Category;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.ArticleService;
import com.zbin.cisp.service.CategoryService;
import com.zbin.cisp.service.UserService;
import com.zbin.cisp.vo.ArticleVO;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Zbin on 2019-02-13
 */
@Controller
public class PageController {

  @Resource
  CategoryService categoryService;

  @Resource
  UserService userService;

  @Resource
  ArticleService articleService;

  @RequestMapping("/")
  public void test(HttpServletResponse response) throws Exception {
    response.sendRedirect("/index");
  }

  @RequestMapping("/index")
  public String index(HttpServletRequest request) {
    List<ArticleVO> articleList = articleService.getIndexArticles();
    request.getSession().setAttribute("articleList", articleList);
    return "index";
  }

  @RequestMapping("/login")
  public String login() {
    return "frontend/user/login";
  }

  @RequestMapping("/register")
  public String register() {
    return "frontend/user/reg";
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
    return "frontend/article/add";
  }

  @RequestMapping("/detail")
  public String detail() {
    return "frontend/detail";
  }

  @RequestMapping("/welcome")
  public String welcome(HttpServletRequest request) {
    int userCount = userService.countAll();
    request.getSession().setAttribute("userCount", userCount);
    return "backend/welcome";
  }

  @RequestMapping("/admin/index")
  public String adminIndex() {
    return "backend/index";
  }

  @RequestMapping("/admin/userManager")
  public String userManager(HttpServletRequest request) {
    int userCount = userService.countAll();
    List<User> userList = userService.getUsers();
    request.getSession().setAttribute("userCount", userCount);
    request.getSession().setAttribute("userList", userList);
    return "backend/member/userManager";
  }

  @RequestMapping("/user/set")
  public String userSet() {
    return "frontend/user/set";
  }

  @RequestMapping("/article/{id}")
  public String articleDeatil(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
    ArticleVO articleVO = articleService.getArticleById(id);
    request.getSession().setAttribute("article", articleVO);
    return "/frontend/article/detail";
  }

  @RequestMapping("/user/center")
  public String userCenter(HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute("user");
    List<Article> myArticles = articleService.getArticlesByUserId(user.getId());
    request.getSession().setAttribute("myArticles", myArticles);
    return "frontend/user/center";
  }

  @RequestMapping("/notice")
  public String notice() {
    return "/notice";
  }
}
