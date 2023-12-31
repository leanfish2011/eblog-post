package com.tim.eblog.post.vo.blog.archive;

import lombok.Data;

/**
 * @author：tim
 * @date：2023-04-15 21:26
 * @description：按照年归档总览
 */
@Data
public class ArchiveSum {

  /**
   * 年份
   */
  private String year;

  /**
   * 博客数量
   */
  private Integer count;
}
