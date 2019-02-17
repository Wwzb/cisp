package com.zbin.cisp;

import com.zbin.cisp.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Zbin on 2019-02-17
 */
@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean someFilterRegistration1() {
    //新建过滤器注册类
    FilterRegistrationBean registration = new FilterRegistrationBean();
    // 添加写好的过滤器
    registration.setFilter(new AuthFilter());
    // 设置过滤器的URL模式
    registration.addUrlPatterns("/*");
    return registration;
  }
}
