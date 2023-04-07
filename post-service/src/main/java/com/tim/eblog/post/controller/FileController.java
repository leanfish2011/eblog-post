package com.tim.eblog.post.controller;

import com.tim.eblog.post.service.FileService;
import com.tim.eblog.post.vo.file.FileResp;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author：tim
 * @date：2023-04-06 15:03
 * @description：
 */
@Api(tags = "文件管理")
@RestController
@RequestMapping("${api.version.module}/file")
public class FileController {

  @Autowired
  private FileService fileService;

  @ApiOperation(value = "文件上传")
  @PostMapping("/upload")
  public Message<FileResp> upload(@RequestParam(name = "file") CommonsMultipartFile file) {
    FileResp fileResp = fileService.upload(file);
    return Message.success(fileResp);
  }

}