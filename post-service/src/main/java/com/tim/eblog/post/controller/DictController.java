package com.tim.eblog.post.controller;

import com.tim.eblog.post.service.DictService;
import com.tim.eblog.post.vo.dict.DictItem;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：tim
 * @date：2023-12-30 18:03
 * @description：
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping("${api.version.module}/dict")
public class DictController {

  @Autowired
  private DictService dictService;

  @ApiOperation(value = "列出所有字典")
  @RequestMapping(method = RequestMethod.GET)
  public Message<List<DictItem>> listDict() {
    return Message.success(dictService.listDictItem());
  }
}
