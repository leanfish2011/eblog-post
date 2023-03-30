package com.tim.eblog.post.service.impl;

import com.tim.eblog.post.ao.User;
import com.tim.eblog.post.component.RequestManager;
import com.tim.eblog.post.component.TokenManager;
import com.tim.eblog.post.exception.BadParameterException;
import com.tim.eblog.post.exception.InvalidTokenException;
import com.tim.eblog.post.service.AccessService;
import com.tim.eblog.post.vo.LoginReq;
import com.tim.eblog.post.vo.LoginResp;
import com.tim.eblog.post.vo.TokenModel;
import com.tim.exception.type.NotFoundException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class AccessServiceImpl implements AccessService {

  @Autowired
  private TokenManager tokenManager;

  @Autowired
  private RequestManager requestManager;

  @Value("${login.admin.code:admin}")
  private String userCode;

  @Value("${login.admin.password:123123}")
  private String password;

  @Override
  public LoginResp login(LoginReq loginReq) {
    String inputUserCode = loginReq.getUserCode();
    if (!userCode.equals(inputUserCode)) {
      log.warn("用户名错误，用户名：{}", inputUserCode);
      throw new BadParameterException("用户名错误！");
    }

    String inputPassword = loginReq.getPassword();
    if (!password.equals(inputPassword)) {
      log.warn("密码错误，密码：{}", inputPassword);
      throw new BadParameterException("密码错误！");
    }

    User user = new User();
    BeanUtils.copyProperties(loginReq, user);

    return this.afterLogin(user);
  }

  @Override
  public Boolean logout() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      log.warn("token为空");
      return true;
    }

    tokenManager.deleteToken(token);
    return true;
  }

  @Override
  public TokenModel profile() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      log.warn("token为空");
      throw new NotFoundException("登录信息为空！");
    }

    TokenModel tokenModel = tokenManager.getTokenModel(token);
    if (tokenModel == null) {
      log.warn("未找到用户信息，token:{}", token);
      throw new NotFoundException("未找到用户信息！");
    }

    return tokenModel;
  }

  @Override
  public Boolean check() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      log.warn("token为空");
      throw new NotFoundException("登录信息为空！");
    }

    boolean isExist = tokenManager.checkToken(token);
    if (!isExist) {
      log.warn("token无效，为空或不存在，token:{}", token);
      throw new InvalidTokenException("登录信息失效！");
    }

    return true;
  }

  /**
   * 登录成功后处理
   *
   * @param user 登录成功的用户
   * @return 登录成功后信息
   */
  private LoginResp afterLogin(User user) {
    LoginResp loginResp = new LoginResp();
    loginResp.setToken(UUID.randomUUID().toString());
    loginResp.setUserCode(user.getUserCode());

    //token存储
    tokenManager.saveTokenModel(loginResp);

    return loginResp;
  }

}
