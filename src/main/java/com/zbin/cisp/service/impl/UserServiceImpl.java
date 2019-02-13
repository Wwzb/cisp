package com.zbin.cisp.service.impl;

import com.zbin.cisp.dao.UserMapper;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by Zbin on 2019-02-13
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  UserMapper userMapper;

  @Override
  public Boolean loginCheck(User user) {
    User tmpUser = userMapper.selectByUsername(user.getUsername());
    if (tmpUser != null && tmpUser.getPassword().equals(user.getPassword())) {
      return true;
    }
    return false;
  }
}
