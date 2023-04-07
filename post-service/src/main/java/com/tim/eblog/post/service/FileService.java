package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.file.FileResp;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author：tim
 * @date：2023-04-06 15:03
 * @description：
 */
public interface FileService {

  FileResp upload(CommonsMultipartFile multipartFile);

  boolean remove(String url);
}
