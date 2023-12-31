package com.tim.eblog.post.vo.dict;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：20-6-30 下午11:26
 * @description：博客查询返回结果
 */
@Data
public class DictSearchData {

  private Integer allTotal;

  private Integer currentTotal;

  private List<DictSearchResp> list;
}