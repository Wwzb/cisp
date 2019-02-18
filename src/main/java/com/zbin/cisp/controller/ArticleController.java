package com.zbin.cisp.controller;

import com.zbin.cisp.domain.Article;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.ArticleService;
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
}
