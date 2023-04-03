package com.tim.eblog.post.vo.login;

import java.io.Serializable;
import lombok.Data;

/**
 * @author：tim
 * @date：20-3-1 下午6:49
 * @description： 登录成功的用户信息
 */
@Data
public class LoginResp implements Serializable {

  /**
   * 用户token
   */
  private String token;

  /**
   * 用户编码
   */
  private String userCode;

}
