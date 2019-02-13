package com.zbin.cisp.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zbin on 2019-02-13
 */
@Controller
public class TestController {


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

}
