package com.tim.eblog.post.vo;

import lombok.Data;

/**
 * @author：tim
 * @date：20-3-7 下午9:05
 * @description：
 */
@Data
public class BlogUpdate {

  private String id;

  private String name;

  private String url;

  private String remark;

  private String tag;

  private Integer sortNum;

}
