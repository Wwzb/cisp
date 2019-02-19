package com.zbin.cisp.controller;

import com.zbin.cisp.domain.Article;
import com.zbin.cisp.domain.Category;
import com.zbin.cisp.domain.Comment;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.ArticleService;
import com.zbin.cisp.service.CategoryService;
import com.zbin.cisp.service.CommentService;
import com.zbin.cisp.utils.FileUtil;
import com.zbin.cisp.utils.ReturnJson;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Zbin on 2019-02-15
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

  @Resource
  ArticleService articleService;

  @Resource
  CategoryService categoryService;

  @Resource
  CommentService commentService;

  @RequestMapping("/uploadImg")
  @ResponseBody
  public ReturnJson upload(HttpServletRequest request, MultipartFile file) {
    String imgUrl = FileUtil.upload(file);
    Map<String, String> imgMap = new HashMap<>();
    imgMap.put("src", imgUrl);
    imgMap.put("title", file.getOriginalFilename());
    return new ReturnJson("上传成功", imgMap);
  }

  @RequestMapping("/add")
  @ResponseBody
  public ReturnJson add(HttpServletRequest request, @RequestBody Article article) {
    try {
      User user = (User) request.getSession().getAttribute("user");
      article.setUserId(user.getId());
      articleService.create(article);
      return new ReturnJson(0, "发布成功");
    } catch (Exception e) {
      return new ReturnJson(1, "发布失败");
    }
  }

  @RequestMapping("/addCategory")
  @ResponseBody
  public ReturnJson addCategory(HttpServletRequest request, @RequestBody Category category) {
    try {
      if (category.getName() != null) {
        categoryService.create(category);
        return new ReturnJson(0, "新增分类成功");
      } else {
        return new ReturnJson(1, "分类名不能为空");
      }
    } catch (Exception e) {
      return new ReturnJson(1, "新增分类失败");
    }
  }

  @RequestMapping("/delCategory")
  @ResponseBody
  public ReturnJson delCategory(HttpServletRequest request, @RequestBody Category category) {
    try {
      if (category.getId() != null) {
        categoryService.deleteById(category.getId());
        return new ReturnJson(0, "删除分类成功");
      } else {
        return new ReturnJson(1, "删除分类失败");
      }
    } catch (Exception e) {
      return new ReturnJson(1, "新增分类失败");
    }
  }

  @RequestMapping("/updateCategory")
  @ResponseBody
  public ReturnJson updateCategory(HttpServletRequest request, @RequestBody Category category) {
    try {
      if (category.getId() != null && category.getName() != null) {
        categoryService.updateById(category);
        return new ReturnJson(0, "修改分类成功");
      } else {
        return new ReturnJson(1, "修改分类失败");
      }
    } catch (Exception e) {
      return new ReturnJson(1, "修改分类失败");
    }
  }

  @RequestMapping("/addComment")
  @ResponseBody
  public ReturnJson addComment(@RequestBody Comment comment) {
    try {
      commentService.create(comment);
      return new ReturnJson("评论成功");
    } catch (Exception e) {
      return new ReturnJson(1, "评论失败");
    }
  }
}
