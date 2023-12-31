package com.tim.eblog.post.vo.dict;

import java.util.Date;
import lombok.Data;

/**
 * @author：tim
 * @date：2023-12-30 17:57
 * @description：
 */
@Data
public class DictResp {

  private String title;

  private String id;

  private Date createTime;

  private String creatorId;

  private Date modifiedTime;

  private String modifierId;

  private String remark;

  private String content;
}
