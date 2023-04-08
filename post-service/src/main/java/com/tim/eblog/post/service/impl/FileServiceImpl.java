package com.tim.eblog.post.service.impl;

import com.tim.eblog.post.exception.BadParameterException;
import com.tim.eblog.post.service.FileService;
import com.tim.eblog.post.vo.file.FileResp;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author：tim
 * @date：2023-04-06 15:04
 * @description：
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

  @Value("${file.storage.root:/opt/}")
  private String storageRoot;

  @Value("#{'${file.allow.suffix:gif,png,jpg,jpeg,bpm,svg}'.split(',')}")
  private List<String> suffixList;

  @Value("${file.suffix.path:/img}")
  private String suffixPath;

  @Override
  public FileResp upload(CommonsMultipartFile multipartFile) {
    fileCheck(multipartFile);
    String path = suffixPath;
    String originalFilename = multipartFile.getOriginalFilename();
    String suffix = Objects.requireNonNull(originalFilename)
        .substring(originalFilename.lastIndexOf("."));
    String fileName = System.currentTimeMillis() + "-"
        + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase()
        + suffix;
    File file = new File(storageRoot + File.separator + fileName);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }

    try {
      multipartFile.transferTo(file);
      path = path + "/" + fileName;
    } catch (IOException e) {
      log.error(e.toString());
      throw new BadParameterException("文件上传失败");
    }

    FileResp fileResp = new FileResp();
    fileResp.setUrl(path);
    fileResp.setUploaded(true);
    fileResp.setFilename(originalFilename);

    return fileResp;
  }

  @Override
  public boolean remove(String url) {
    String path = storageRoot + File.separator + url;
    File file = new File(path);
    if (file.exists() && file.isFile()) {
      return file.delete();
    }

    return false;
  }

  private void fileCheck(CommonsMultipartFile multipartFile) {
    if (multipartFile == null || multipartFile.isEmpty()) {
      throw new BadParameterException("文件为空");
    }

    if (StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
      throw new BadParameterException("文件后缀名为空");
    }

    if (!isAllow(multipartFile.getOriginalFilename())) {
      throw new BadParameterException("不支持的文件类型");
    }
  }

  private boolean isAllow(String fileName) {
    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

    return suffixList.contains(suffix);
  }

}
