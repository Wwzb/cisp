package com.zbin.cisp.dao;

import com.zbin.cisp.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Zbin on 2019-02-13
 */
@Mapper
@Repository
public interface UserMapper {

  User selectByUsername(String username);

  int insert(User user);
}
