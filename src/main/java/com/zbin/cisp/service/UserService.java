package com.zbin.cisp.service;

import com.zbin.cisp.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  Boolean loginCheck(User user);
}
