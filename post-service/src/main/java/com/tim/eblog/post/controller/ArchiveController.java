package com.tim.eblog.post.controller;

import com.tim.eblog.post.service.ArchiveService;
import com.tim.eblog.post.vo.blog.ArchiveData;
import com.tim.eblog.post.vo.blog.ArchiveSumData;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：tim
 * @date：2023-12-30 14:31
 * @description：
 */
@Api(tags = "归档管理")
@RestController
@RequestMapping("${api.version.module}/blog")
public class ArchiveController {

  @Autowired
  private ArchiveService archiveService;

  @ApiOperation(value = "查看归档总揽")
  @RequestMapping(value = "/archive", method = RequestMethod.GET)
  public Message<ArchiveSumData> selectArchiveSum() {
    return Message.success(archiveService.selectArchiveSum());
  }

  @ApiOperation(value = "按年份查看归档详情")
  @RequestMapping(value = "/archive/{year}", method = RequestMethod.GET)
  public Message<ArchiveData> selectArchiveByYear(@PathVariable String year) {
    return Message.success(archiveService.selectArchiveByYear(year));
  }
}
