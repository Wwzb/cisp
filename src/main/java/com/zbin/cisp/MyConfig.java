package com.zbin.cisp;

import com.zbin.cisp.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Zbin on 2019-02-13
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

  private static final String[] EXCLUDE_URLS = {"/static/**", "/login", "/index", "/",
    "/article/**", "/register", "/doLogin", "/adminLogin", "/doRegister", "/admin"};

  //配置静态资源路径
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
  }

  //配置登录拦截器
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
      .excludePathPatterns(EXCLUDE_URLS);
  }
}
