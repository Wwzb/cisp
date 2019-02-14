package com.zbin.cisp.service.impl;

import com.zbin.cisp.dao.UserMapper;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.UserService;
import com.zbin.cisp.utils.PwdCheck;
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
    return PwdCheck.validPwd(user.getPassword(), tmpUser.getPassword());
  }

  @Override
  public void register(User user) {
    user.setPassword(PwdCheck.bryptPwd(user.getPassword()));
    userMapper.insert(user);
  }

  @Override
  public Boolean isUsernameExsit(String username) {
    return userMapper.selectByUsername(username) != null;
  }
}
