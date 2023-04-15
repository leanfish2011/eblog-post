package com.tim.eblog.post.vo.blog;

import lombok.Data;

/**
 * @author：tim
 * @date：20-3-7 下午9:05
 * @description：博客创建
 */
@Data
public class BlogAdd {

  private String title;

  private String content;

  private String [] arrayTag;

}
