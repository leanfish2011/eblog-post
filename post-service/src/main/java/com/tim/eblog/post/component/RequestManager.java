package com.tim.eblog.post.component;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author：tim
 * @date：20-3-1 下午9:31
 * @description：
 */
@Component
public class RequestManager {

  @Autowired
  protected HttpServletRequest request;

  /**
   * 获取当前登录用户token
   */
  public String getAccessToken() {
    String token =
        request.getHeader("authorization") == null ? request.getHeader("Authorization")
            : request.getHeader("authorization");

    return StringUtils.isEmpty(token) ? null : token.trim();
  }

  /**
   * 获取当前请求的资源路径
   */
  public String getRequestURI() {
    return request.getRequestURI();
  }

  /**
   * 获取当前请求方法类型
   */
  public String getRequestMethod() {
    return request.getMethod();
  }

}
