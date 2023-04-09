package com.tim.eblog.post.vo.blog;

import java.util.Date;
import lombok.Data;

@Data
public class BlogSearchResp {

  private String id;

  private Date createTime;

  private String creatorId;

  private Date modifiedTime;

  private String modifierId;

  private String title;

  private String remark;

  private Integer comCount;

  private Integer readCount;

  private Integer suggestCount;
}
