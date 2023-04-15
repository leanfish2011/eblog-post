package com.tim.eblog.post.vo.blog;

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

  private Integer readCount;

  private String remark;

  private String [] arrayTag;

  private String content;

}