package com.zbin.cisp.controller;

import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.UserService;
import com.zbin.cisp.utils.ReturnJson;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zbin on 2019-02-13
 */
@Controller
public class TestController {

  @Resource
  UserService userService;

  @RequestMapping("/")
  public String test() {
    return "login";
  }

  @RequestMapping("/index")
  public String index() {
    return "index";
  }

  @RequestMapping("/errora")
  public String error() {
    return "frontend/404";
  }

  @RequestMapping("/try")
  public ModelAndView trys(ModelAndView modelAndView) {
    modelAndView.setViewName("test");

    List<String> userList = new ArrayList<>();
    userList.add("admin");
    userList.add("user1");
    userList.add("user2");

    modelAndView.addObject("userList", userList);
    return modelAndView;
  }

  @RequestMapping("/doLogin")
  @ResponseBody
  public ReturnJson doLogin(HttpServletRequest request, @RequestBody User user) {
    boolean isLogin = userService.loginCheck(user);
    if (isLogin) {
      return new ReturnJson(0, "登录成功", 0, "");
    } else {
      return new ReturnJson(0, "用户名或密码错误", 0, "");
    }
  }

}
