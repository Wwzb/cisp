package com.zbin.cisp.controller;

import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.UserService;
import com.zbin.cisp.utils.ReturnJson;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Zbin on 2019-02-13
 */
@Controller
public class PageController {

  @Resource
  UserService userService;

  @RequestMapping("/")
  public String test() {
    return "register";
  }

  @RequestMapping("/index")
  public String index() {
    return "index";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/register")
  public String trys() {
    return "register";
  }

  @RequestMapping("/doLogin")
  @ResponseBody
  public ReturnJson doLogin(@RequestBody User user) {
    boolean isLogin = userService.loginCheck(user);
    if (isLogin) {
      return new ReturnJson(0, "登录成功", 0, "");
    } else {
      return new ReturnJson(1, "用户名或密码错误", 0, "");
    }
  }

}
