package com.zbin.cisp.controller;

import com.zbin.cisp.domain.Category;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.ArticleService;
import com.zbin.cisp.service.CategoryService;
import com.zbin.cisp.service.CommentService;
import com.zbin.cisp.service.UserService;
import com.zbin.cisp.vo.ArticleVO;
import com.zbin.cisp.vo.CommentVO;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @Resource
  CommentService commentService;

  @RequestMapping("/")
  public void test(HttpServletResponse response) throws Exception {
    response.sendRedirect("/index");
  }

  @RequestMapping("/index")
  public String index(HttpServletRequest request, @RequestParam(required = false) Integer cId,
    @RequestParam(required = false) String order) {
    List<Category> list = categoryService.getAllCategory();
    request.getSession().setAttribute("category", list);
    List<ArticleVO> articleList;
    if (cId == null || cId == 0) {
      articleList = articleService.getIndexArticles();
      for (ArticleVO articleVO : articleList) {
        articleVO.setCommentCount(commentService.getCommentByArticleId(articleVO.getId()).size());
      }
    } else {
      articleList = articleService.getArticlesByCategoryId(cId);
      for (ArticleVO articleVO : articleList) {
        articleVO.setCommentCount(commentService.getCommentByArticleId(articleVO.getId()).size());
      }
    }
    if ("hot".equals(order)) {
      articleList.sort(Comparator.comparing(ArticleVO::getCommentCount).reversed());
    }
    request.getSession().setAttribute("cId", cId);
    request.getSession().setAttribute("order", order);
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

  @RequestMapping("/welcome")
  public String welcome(HttpServletRequest request) {
    int userCount = userService.countAll();
    int articleCount = articleService.countAllArticle();
    request.getSession().setAttribute("userCount", userCount);
    request.getSession().setAttribute("articleCount", articleCount);
    return "backend/welcome";
  }

  @RequestMapping("/admin/index")
  public String adminIndex() {
    return "backend/index";
  }

  @RequestMapping("/admin/user/userList")
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
    request.getSession().removeAttribute("commentVOList");
    ArticleVO articleVO = articleService.getArticleById(id);
    List<CommentVO> commentVOList = commentService.getCommentByArticleId(id);
    request.getSession().setAttribute("article", articleVO);
    if (commentVOList.size() != 0) {
      request.getSession().setAttribute("commentVOList", commentVOList);
    }
    return "/frontend/article/detail";
  }

  @RequestMapping("/user/center")
  public String userCenter(HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute("user");
    List<ArticleVO> myArticles = articleService.getArticlesByUserId(user.getId());
    for (ArticleVO articleVO : myArticles) {
      articleVO.setCommentCount(commentService.getCommentByArticleId(articleVO.getId()).size());
    }
    request.getSession().setAttribute("myArticles", myArticles);
    return "frontend/user/center";
  }

  @RequestMapping("/notice")
  public String notice() {
    return "/notice";
  }

  @RequestMapping("/admin/article/articleList")
  public String articleList(HttpServletRequest request) {
    int articleCount = articleService.countAllArticle();
    request.getSession().setAttribute("articleCount", articleCount);
    return "/backend/article/list";
  }

  @RequestMapping("/admin/article/category")
  public String categoryList(HttpServletRequest request) {
    List<Category> categoryList = categoryService.getAllCategory();
    request.getSession().setAttribute("categoryList", categoryList);
    return "/backend/article/category";
  }

  @RequestMapping("/admin/article/category/add")
  public String addCategory(HttpServletRequest request) {
    return "/backend/article/category-add";
  }

  @RequestMapping("/admin/article/category/edit")
  public String editCategory(HttpServletRequest request) {
    return "/backend/article/category-edit";
  }

  @RequestMapping("/user/{id}")
  public String userHome(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
    request.getSession().removeAttribute("commentVOList");
    ArticleVO articleVO = articleService.getArticleById(id);
    List<CommentVO> commentVOList = commentService.getCommentByArticleId(id);
    request.getSession().setAttribute("article", articleVO);
    if (commentVOList.size() != 0) {
      request.getSession().setAttribute("commentVOList", commentVOList);
    }
    return "/frontend/user/home";
  }

}
