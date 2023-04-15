package com.tim.eblog.post.vo.blog;

import lombok.Data;

/**
 * @author：tim
 * @date：20-3-7 下午9:05
 * @description：
 */
@Data
public class BlogUpdate {

  private String id;

  private String title;

  private String content;

  private String [] arrayTag;
}
