package com.tim.eblog.post;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author：tim
 * @date：20-3-7 下午5:26
 * @description：
 */
@ComponentScan({"com.tim"})
@SpringBootApplication
@MapperScan("com.tim.eblog.post.dao")
public class EblogPostApplication {

  public static void main(String[] args) {
    SpringApplication.run(EblogPostApplication.class, args);
  }

}
