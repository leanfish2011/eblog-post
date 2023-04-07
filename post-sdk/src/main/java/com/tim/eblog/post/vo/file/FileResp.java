package com.tim.eblog.post.vo.file;

import lombok.Data;

/**
 * @author：tim
 * @date：2023-04-06 15:24
 * @description：
 */
@Data
public class FileResp {

  private boolean uploaded;
  private String filename;
  private String url;
}
