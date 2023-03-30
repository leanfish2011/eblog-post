package com.tim.eblog.post.controller;

import com.tim.eblog.post.service.AccessService;
import com.tim.eblog.post.vo.LoginReq;
import com.tim.eblog.post.vo.LoginResp;
import com.tim.eblog.post.vo.TokenModel;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作为认证服务，提供功能： 1、生成token； 2、验证token，即通过token查找用户； token管理自己实现。
 *
 * @author tim
 * @time 2018-05-27 19:30:26
 */
@Api(tags = "账号管理")
@RestController
@RequestMapping("${api.version.module}/access")
@Slf4j
public class AccessController {

  @Autowired
  private AccessService accessService;

  @ApiOperation(value = "登录")
  @PostMapping("/login")
  public Message<LoginResp> login(@RequestBody LoginReq loginReq) {
    return Message.success(accessService.login(loginReq));
  }

  @ApiOperation(value = "退出", notes = "需要登录,token放入Header中")
  @GetMapping("/logout")
  public Message logout() {
    accessService.logout();
    return Message.success();
  }

  @ApiOperation(value = "通过token查自己信息", notes = "需要登录,token放入Header中")
  @GetMapping("/profile")
  public Message<TokenModel> profile() {
    return Message.success(accessService.profile());
  }

  @ApiOperation(value = "检查token是否有效", notes = "需要登录,token放入Header中")
  @GetMapping("/check")
  public Message check() {
    accessService.check();
    return Message.success();
  }

}
