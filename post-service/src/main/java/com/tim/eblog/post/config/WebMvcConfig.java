package com.tim.eblog.post.config;

import com.tim.eblog.post.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

  @Autowired
  private LoginInterceptor loginInterceptor;// 登录拦截器

  //token校验和权限校验请求
  @Value("${api.version.module}/**")
  private String addPathPatterns;

  //token校验排除请求
  @Value("#{'${login.exclude.path.patterns}'.split(',')}")
  private String[] LoginExcludePathPatterns;

  // 拦截器链
  // 需要验证：1、token是否有效；
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // addPathPatterns 用于添加拦截规则
    // excludePathPatterns 用于排除拦截
    registry.addInterceptor(loginInterceptor).addPathPatterns(addPathPatterns)
        .excludePathPatterns(LoginExcludePathPatterns);
  }

  // 处理静态资源
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // SpringBoot自动配置本身不会自动把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面。加上映射静态页面和引用包即可。
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

}
