package com.zbin.cisp.service;

import com.zbin.cisp.domain.User;
import java.util.List;

public interface UserService {

  /**
   * 登录校验
   */
  User loginCheck(User user);

  /**
   * 用户注册
   */
  void register(User user);

  /**
   * 用户名校验
   */
  Boolean isUsernameExsit(String username);

  User getUserByUsername(String username);

  int countAll();

  List<User> getUsers();

  void updateUser(User user);

  User getUserById(Integer id);
}
