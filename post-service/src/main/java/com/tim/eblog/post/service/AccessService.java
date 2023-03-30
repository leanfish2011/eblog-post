package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.LoginReq;
import com.tim.eblog.post.vo.LoginResp;
import com.tim.eblog.post.vo.TokenModel;

public interface AccessService {

  /**
   * 用户名密码登录
   *
   * @param loginReq 登录请求参数：用户名、密码
   * @return 登录成功后信息
   */
  LoginResp login(LoginReq loginReq);

  Boolean logout();

  TokenModel profile();

  Boolean check();

}
