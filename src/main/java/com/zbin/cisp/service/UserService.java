package com.zbin.cisp.service;

import com.zbin.cisp.domain.User;
import org.springframework.stereotype.Service;

@Service
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
}
