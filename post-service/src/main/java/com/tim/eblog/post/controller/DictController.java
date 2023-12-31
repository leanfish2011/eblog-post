package com.tim.eblog.post.controller;

import com.tim.eblog.post.service.DictService;
import com.tim.eblog.post.vo.dict.DictItem;
import com.tim.eblog.post.vo.dict.DictResp;
import com.tim.eblog.post.vo.dict.DictSearchData;
import com.tim.eblog.post.vo.dict.DictSearchReq;
import com.tim.eblog.post.vo.dict.DictUpdate;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
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

  @ApiOperation(value = "查看单个数据字典")
  @RequestMapping(value = "/{title}", method = RequestMethod.GET)
  public Message<DictResp> select(@PathVariable String title) {
    return Message.success(dictService.select(title));
  }

  @ApiOperation(value = "查询数据字典列表")
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public Message<DictSearchData> search(@Valid DictSearchReq dictSearchReq) {
    return Message.success(dictService.search(dictSearchReq));
  }

  @ApiOperation(value = "修改数据字典")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(@RequestBody DictUpdate dictUpdate) {
    dictService.update(dictUpdate);
    return Message.success();
  }
}
