package com.zbin.cisp.service.impl;

import com.zbin.cisp.dao.UserMapper;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.UserService;
import com.zbin.cisp.utils.PasswordUtil;
import java.util.List;
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
  public User loginCheck(User user) {
    User tmpUser = userMapper.selectByUsername(user.getUsername());
    boolean isLogin = PasswordUtil.validPwd(user.getPassword(), tmpUser.getPassword());
    if (isLogin) {
      return tmpUser;
    } else {
      return null;
    }
  }

  @Override
  public void register(User user) {
    user.setPassword(PasswordUtil.bryptPwd(user.getPassword()));
    userMapper.insert(user);
  }

  @Override
  public Boolean isUsernameExsit(String username) {
    return userMapper.selectByUsername(username) != null;
  }

  @Override
  public User getUserByUsername(String username) {
    return userMapper.selectByUsername(username);
  }

  @Override
  public int countAll(String search) {
    return userMapper.countAll(search);
  }

  @Override
  public List<User> getUsers(String search) {
    return userMapper.getUsers(search);
  }

  @Override
  public void updateUser(User user) {
    if (user.getPassword() != null) {
      if ("".equals(user.getPassword())) {
        user.setPassword(null);
      } else {
        user.setPassword(PasswordUtil.bryptPwd(user.getPassword()));
      }
    }
    userMapper.updateUser(user);
  }

  @Override
  public User getUserById(Integer id) {
    return userMapper.getUserById(id);
  }

  @Override
  public void deleteById(Integer id) {
    userMapper.deleteById(id);
  }
}
