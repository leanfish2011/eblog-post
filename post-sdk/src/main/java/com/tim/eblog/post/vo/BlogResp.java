package com.tim.eblog.post.vo;

import java.util.Date;
import lombok.Data;

@Data
public class BlogResp {

  private String id;

  private Date createTime;

  private String creatorId;

  private Date modifiedTime;

  private String modifierId;

  private String title;

  private String content;

  private Integer comCount;

  private Integer readCount;

  private Integer suggestCount;

}