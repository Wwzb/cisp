package com.zbin.cisp.controller;

import com.zbin.cisp.domain.Article;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.ArticleService;
import com.zbin.cisp.utils.ReturnJson;
import com.zbin.cisp.utils.UUIDUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
    String szFileName = file.getOriginalFilename();
    String szDateFolder = "";
    String szNewFileName = "";
    String szFilePath;
    String fileType = "jpg,gif,png,bmp,jpeg";
    String ext;
    try {
      if (szFileName != null && szFileName.length() > 0) {
        Date date = new Date();
        szDateFolder = new SimpleDateFormat("yyyy/MM").format(date);
        szFilePath =
          "/Users/admin/Documents/myworkspace/cisp/src/main/resources/static/upload/"
            + szDateFolder;
        File f = new File(szFilePath);
        ext = szFileName.substring(szFileName.lastIndexOf(".") + 1).toLowerCase().trim();
        if (!Arrays.asList(fileType.split(",")).contains(ext)) {
          return new ReturnJson(1, "上传格式不正确,请上传后缀为[.jpg,.gif,.png,.bmp,.jpeg]", 0, "");
        }
        if (!f.exists()) {
          boolean result = f.mkdirs();
        }
        szNewFileName = UUIDUtil.getUUID() + szFileName.substring(szFileName.lastIndexOf("."));
        File newFile = new File(szFilePath + "//" + szNewFileName);
        file.transferTo(newFile);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    User user = new User();
    user.setId((Integer) request.getSession().getAttribute("user"));
    Map<String, String> imgMap = new HashMap<>();
    imgMap.put("src", "/static/upload/" + szDateFolder + "/" + szNewFileName);
    imgMap.put("title", szFileName);
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
