package com.tim.eblog.post.service.impl;

import com.tim.eblog.post.dao.DictMapper;
import com.tim.eblog.post.po.Dict;
import com.tim.eblog.post.service.DictService;
import com.tim.eblog.post.vo.dict.DictItem;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date：2023-12-30 17:59
 * @description：
 */
@Service
@Slf4j
public class DictServiceImpl implements DictService {

  @Autowired
  private DictMapper dictMapper;

  @Override
  public List<DictItem> listDictItem() {
    List<Dict> dictList = dictMapper.selectByExampleWithBLOBs(null);

    List<DictItem> dictItemList = new ArrayList<>();
    for (Dict dict : dictList) {
      DictItem dictItem = new DictItem();
      dictItem.setTitle(dict.getTitle());
      dictItem.setContent(dict.getContent());
      dictItem.setRemark(dict.getRemark());

      dictItemList.add(dictItem);
    }

    return dictItemList;
  }
}
