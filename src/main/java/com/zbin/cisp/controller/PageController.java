package com.zbin.cisp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Zbin on 2019-02-13
 */
@Controller
public class PageController {

  @RequestMapping("/")
  public String test() {
    return "index";
  }

  @RequestMapping("/index")
  public String index() {
    return "index";
  }

  @RequestMapping("/login")
  public String login() {
    return "frontend/login";
  }

  @RequestMapping("/register")
  public String trys() {
    return "frontend/register";
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
}
