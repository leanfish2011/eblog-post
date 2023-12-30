package com.tim.eblog.post.controller;

import com.tim.eblog.post.service.TagService;
import com.tim.eblog.post.vo.blog.TagBlogData;
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
 * @date：2023-12-30 14:02
 * @description：
 */
@Api(tags = "标签管理")
@RestController
@RequestMapping("${api.version.module}/blog")
public class TagController {

  @Autowired
  private TagService tagService;

  @ApiOperation(value = "查看博客全部标签")
  @RequestMapping(value = "/tag", method = RequestMethod.GET)
  public Message<List<String>> listTag() {
    return Message.success(tagService.listTag());
  }

  @ApiOperation(value = "查看标签下的博客")
  @RequestMapping(value = "/tag/blog", method = RequestMethod.GET)
  public Message<List<TagBlogData>> queryTagBlog() {
    return Message.success(tagService.queryTagBlog());
  }
}
