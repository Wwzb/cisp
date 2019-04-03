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
import javax.servlet.http.HttpServletResponse;
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
      if ("管理员".equals(newUser.getType())) {
        request.getSession().setAttribute("adminUser", newUser);
      }
      request.getSession().setMaxInactiveInterval(1800);
      return new ReturnJson(0, "登录成功", 0, "");
    } else {
      return new ReturnJson(1, "用户名或密码错误", 0, "");
    }
  }

  @PostMapping("/adminLogin")
  @ResponseBody
  public ReturnJson adminLogin(HttpServletRequest request, @RequestBody User user) {
    try {
      User adminUser = userService.getUserByUsername(user.getUsername());
      if (adminUser == null) {
        return new ReturnJson(1, "用户不存在!", 0, "");
      }
      if (!PasswordUtil.validPwd(user.getPassword(), adminUser.getPassword())) {
        return new ReturnJson(1, "用户名或密码错误", 0, "");
      }
      if (!"管理员".equals(adminUser.getType())) {
        return new ReturnJson(1, "该用户不是管理员!", 0, "");
      }
      request.getSession().setAttribute("adminUser", adminUser);
      return new ReturnJson(0, "登录成功");
    } catch (Exception e) {
      return new ReturnJson(1, "登录失败");
    }
  }

  @RequestMapping("/logout")
  public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.getSession().invalidate();
    response.sendRedirect("/index");
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

  @PostMapping("/user/changeStatus")
  @ResponseBody
  public ReturnJson userChangeStatus(HttpServletRequest request, @RequestBody String param) {
    try {
      JSONObject json = JSON.parseObject(param);
      String username = json.getString("username");
      String status = json.getString("status");
      User user = new User();
      user.setUsername(username);
      user.setStatus(status);
      userService.updateUser(user);
      return new ReturnJson(0, "修改状态成功");
    } catch (Exception e) {
      return new ReturnJson("修改状态失败");
    }
  }

  @PostMapping("/user/delete")
  @ResponseBody
  public ReturnJson userDelete(HttpServletRequest request, @RequestBody String param) {
    try {
      JSONObject json = JSON.parseObject(param);
      Integer id = json.getInteger("id");
      userService.deleteById(id);
      return new ReturnJson(0, "删除成功");
    } catch (Exception e) {
      return new ReturnJson("删除失败");
    }
  }
}
