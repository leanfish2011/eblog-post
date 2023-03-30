package com.tim.eblog.post.interceptor;

import com.tim.eblog.post.service.AccessService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器，检查用户是否登录
 *
 * @author tim
 * @time 2018-05-12 17:00:58
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

  @Autowired
  private AccessService accessService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    //针对博客查看接口，不拦截
    if (request.getRequestURI().contains("/blog") && request.getMethod()
        .equals(RequestMethod.GET)) {
      return true;
    }

    //除了配置文件中不包含接口，其他接口都要检查token是否有效
    return accessService.check();
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) {
  }

}
