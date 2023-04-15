package com.tim.eblog.post.controller;

import com.tim.eblog.post.service.BlogService;
import com.tim.eblog.post.vo.blog.ArchiveData;
import com.tim.eblog.post.vo.blog.ArchiveSumData;
import com.tim.eblog.post.vo.blog.BlogAdd;
import com.tim.eblog.post.vo.blog.BlogResp;
import com.tim.eblog.post.vo.blog.BlogSearchData;
import com.tim.eblog.post.vo.blog.BlogSearchReq;
import com.tim.eblog.post.vo.blog.BlogUpdate;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：tim
 * @date：20-3-7 下午5:29
 * @description：
 */
@Api(tags = "博客管理")
@RestController
@RequestMapping("${api.version.module}/blog")
public class BlogController {

  @Autowired
  private BlogService blogService;

  @ApiOperation(value = "新增博客")
  @RequestMapping(method = RequestMethod.POST)
  public Message add(@RequestBody BlogAdd blogAdd) {
    blogService.add(blogAdd);
    return Message.success();
  }

  @ApiOperation(value = "删除博客")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Message delete(@PathVariable String id) {
    blogService.delete(id);
    return Message.success();
  }

  @ApiOperation(value = "修改博客")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(@RequestBody BlogUpdate blogUpdate) {
    blogService.update(blogUpdate);
    return Message.success();
  }

  @ApiOperation(value = "查看单个博客")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Message<BlogResp> select(@PathVariable String id) {
    return Message.success(blogService.select(id));
  }

  @ApiOperation(value = "查询博客列表")
  @RequestMapping(method = RequestMethod.GET)
  public Message<BlogSearchData> search(@Valid BlogSearchReq blogSearchReq) {
    return Message.success(blogService.search(blogSearchReq));
  }

  @ApiOperation(value = "查看归档总揽")
  @RequestMapping(value = "/archive", method = RequestMethod.GET)
  public Message<ArchiveSumData> selectArchiveSum() {
    return Message.success(blogService.selectArchiveSum());
  }

  @ApiOperation(value = "按年份查看归档详情")
  @RequestMapping(value = "/archive/{year}", method = RequestMethod.GET)
  public Message<ArchiveData> selectArchiveByYear(@PathVariable String year) {
    return Message.success(blogService.selectArchiveByYear(year));
  }

}
