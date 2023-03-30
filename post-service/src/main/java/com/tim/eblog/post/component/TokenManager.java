package com.tim.eblog.post.component;

import com.tim.eblog.post.util.ExpiryMap;
import com.tim.eblog.post.vo.LoginResp;
import com.tim.eblog.post.vo.TokenModel;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 通过 Redis 存储和验证 token 的实现类
 *
 * @author tim
 * @createTime 2018年5月10日-下午2:35:47
 */
@Component
public class TokenManager {

  @Value("${token.expires.minutes:2}")
  private int expiresMin;

  ExpiryMap<String, TokenModel> tokenMap;

  @PostConstruct
  public void initTokenManager() {
    tokenMap = new ExpiryMap<>();
  }

  /**
   * 登录返回对象存入redis
   *
   * @param loginResp 登录返回对象
   */
  public void saveTokenModel(LoginResp loginResp) {
    TokenModel tokenModel = new TokenModel();
    tokenModel.setToken(loginResp.getToken());
    tokenModel.setLoginResp(loginResp);

    tokenMap.put(loginResp.getToken(), tokenModel, expiresMin * 60 * 1000);
  }

  /**
   * 根据token获取用户信息
   */
  public TokenModel getTokenModel(String token) {
    if (token == null) {
      return null;
    }

    TokenModel tokenModel = tokenMap.get(token);

    if (tokenModel == null) {
      return null;
    }

    // 如果验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
    // TODO 需要实现expire函数
    tokenMap.put(token, tokenModel, expiresMin * 60 * 1000);

    return tokenModel;
  }

  /**
   * 删除token
   */
  public void deleteToken(String token) {
    tokenMap.remove(token);
  }

  /**
   * 检查token是否有效
   */
  public boolean checkToken(String token) {
    if (StringUtils.isEmpty(token)) {
      return false;
    }

    TokenModel tokenModel = getTokenModel(token);
    if (tokenModel == null) {
      return false;
    }

    return true;
  }
}
