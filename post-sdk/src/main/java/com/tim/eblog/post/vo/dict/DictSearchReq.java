package com.tim.eblog.post.vo.dict;

import javax.validation.constraints.Min;
import lombok.Data;

/**
 * @author：tim
 * @date：2023-04-15 18:43
 * @description：
 */
@Data
public class DictSearchReq {

  private String title;

  @Min(value = 1, message = "分页起始页不能小于1")
  private Integer pageNo;

  @Min(value = 1, message = "每页条数不能小于1")
  private Integer pageSize;
}
