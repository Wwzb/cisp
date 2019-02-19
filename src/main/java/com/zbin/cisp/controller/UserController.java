package com.zbin.cisp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.UserService;
import com.zbin.cisp.utils.FileUtil;
import com.zbin.cisp.utils.PasswordUtil;
import com.zbin.cisp.utils.ReturnJson;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Zbin on 2019-02-14
 */
@Controller
public class UserController {

  @Resource
  UserService userService;

  @PostMapping("/doRegister")
  @ResponseBody
  public ReturnJson doRegister(@RequestBody User user) {
    try {
      if (userService.isUsernameExsit(user.getUsername())) {
        return new ReturnJson(2, "用户已存在!", 0, "");
      }
      userService.register(user);
      return new ReturnJson(0, "注册成功", 0, "");
    } catch (Exception e) {
      return new ReturnJson(1, "发生了一些错误", 0, "");
    }
  }

  @PostMapping("/doLogin")
  @ResponseBody
  public ReturnJson doLogin(HttpServletRequest request, @RequestBody User user) {
    User newUser = userService.loginCheck(user);
    if (newUser != null) {
      request.getSession().setAttribute("user", newUser);
      request.getSession().setMaxInactiveInterval(1800);
      return new ReturnJson(0, "登录成功", 0, "");
    } else {
      return new ReturnJson(1, "用户名或密码错误", 0, "");
    }
  }

  @PostMapping("/adminLogin")
  @ResponseBody
  public ReturnJson adminLogin(@RequestBody User user) {
    User tmpUser = userService.getUserByUsername(user.getUsername());
    if (tmpUser == null) {
      return new ReturnJson(1, "用户不存在!", 0, "");
    }
    if (!PasswordUtil.validPwd(user.getPassword(), tmpUser.getPassword())) {
      return new ReturnJson(1, "用户名或密码错误", 0, "");
    }
    if ("normal".equals(tmpUser.getType())) {
      return new ReturnJson(1, "该用户不是管理员!", 0, "");
    }
    return new ReturnJson(0, "登录成功", 0, "");
  }

  @RequestMapping("/logout")
  public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "index";
  }

  @PostMapping("/user/update")
  @ResponseBody
  public ReturnJson userUpdate(HttpServletRequest request, @RequestBody User user) {
    try {
      userService.updateUser(user);
      request.getSession().setAttribute("user", userService.getUserByUsername(user.getUsername()));
      return new ReturnJson("修改成功");
    } catch (Exception e) {
      return new ReturnJson("修改失败");
    }
  }

  @PostMapping("/user/setAvatar")
  @ResponseBody
  public ReturnJson userSetAvatar(HttpServletRequest request, MultipartFile file) {
    try {
      String imgUrl = FileUtil.upload(file);
      User user = (User) request.getSession().getAttribute("user");
      user.setAvatar(imgUrl);
      userService.updateUser(user);
      request.getSession().setAttribute("user", userService.getUserByUsername(user.getUsername()));
      Map<String, String> imgMap = new HashMap<>();
      imgMap.put("src", imgUrl);
      imgMap.put("title", file.getOriginalFilename());
      return new ReturnJson("设置成功", imgMap);
    } catch (Exception e) {
      return new ReturnJson("上传失败");
    }

  }

  @PostMapping("/user/changePwd")
  @ResponseBody
  public ReturnJson userChangePwd(HttpServletRequest request, @RequestBody String param) {
    try {
      JSONObject json = JSON.parseObject(param);
      String oldPwd = json.getString("oldPwd");
      String newPwd = json.getString("newPwd");
      User user = (User) request.getSession().getAttribute("user");
      user.setPassword(oldPwd);
      user = userService.loginCheck(user);
      if (user == null) {
        return new ReturnJson(1, "密码不正确");
      } else {
        user.setPassword(PasswordUtil.bryptPwd(newPwd));
        userService.updateUser(user);
        return new ReturnJson(0, "修改成功");
      }
    } catch (Exception e) {
      return new ReturnJson("修改失败");
    }
  }
}
