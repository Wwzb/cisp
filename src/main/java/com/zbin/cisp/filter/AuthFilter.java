package com.zbin.cisp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zbin on 2019-02-17
 */

public class AuthFilter implements Filter {

  private static String[] includeUrls = new String[]{"/login", "/register", "/index", "/",
    "/doLogin", "/doRegister"};

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {

  }

  //判断是否登录
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
    FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    HttpSession session = request.getSession(false);
    String uri = request.getRequestURI();
    boolean needFilter = isNeedFilter(uri);
    if (!needFilter) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      if (session != null && session.getAttribute("user") != null) {
        filterChain.doFilter(servletRequest, servletResponse);
      } else {
        response.sendRedirect("/login");
      }
    }

  }

  private boolean isNeedFilter(String uri) {

    for (String includeUrl : includeUrls) {
      if (includeUrl.equals(uri)) {
        return false;
      }
    }
    return true;
  }
}
