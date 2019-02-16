package com.zbin.cisp.controller;

import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.UserService;
import com.zbin.cisp.utils.PasswordUtil;
import com.zbin.cisp.utils.ReturnJson;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Zbin on 2019-02-14
 */
@Controller
public class UserController {

  @Resource
  UserService userService;

  @RequestMapping("/doRegister")
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

  @RequestMapping("/doLogin")
  @ResponseBody
  public ReturnJson doLogin(HttpServletRequest request, @RequestBody User user) {
    User newUser = userService.loginCheck(user);
    if (newUser != null) {
      request.getSession().setAttribute("user", newUser.getId());
      return new ReturnJson(0, "登录成功", 0, "");
    } else {
      return new ReturnJson(1, "用户名或密码错误", 0, "");
    }
  }

  @RequestMapping("/adminLogin")
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
}
