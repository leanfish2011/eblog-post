package com.tim.eblog.post.vo.login;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author：tim
 * @date：20-3-1 下午6:38
 * @description：
 */
@Data
public class LoginReq {

  @NotBlank
  private String userCode;

  @NotBlank
  private String password;
}
