package com.tim.eblog.post.service.impl;

import com.tim.eblog.post.dao.DictMapper;
import com.tim.eblog.post.po.Dict;
import com.tim.eblog.post.po.DictExample;
import com.tim.eblog.post.po.DictExample.Criteria;
import com.tim.eblog.post.service.DictService;
import com.tim.eblog.post.vo.dict.DictItem;
import com.tim.eblog.post.vo.dict.DictResp;
import com.tim.eblog.post.vo.dict.DictSearchData;
import com.tim.eblog.post.vo.dict.DictSearchReq;
import com.tim.eblog.post.vo.dict.DictSearchResp;
import com.tim.eblog.post.vo.dict.DictUpdate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author：tim
 * @date：2023-12-30 17:59
 * @description：
 */
@Service
@Slf4j
public class DictServiceImpl implements DictService {

  @Value("${login.admin.code:admin}")
  private String userCode;

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

  @Override
  public DictSearchData search(DictSearchReq dictSearchReq) {
    DictExample dictExample = new DictExample();
    Criteria criteria = dictExample.createCriteria();

    if (!StringUtils.isEmpty(dictSearchReq.getTitle())) {
      criteria.andTitleLike("%" + dictSearchReq.getTitle() + "%");
    }

    int allTotal = dictMapper.countByExample(dictExample);

    dictExample.setOrderByClause(" create_time desc");
    Integer pageNo = dictSearchReq.getPageNo();
    Integer pageSize = dictSearchReq.getPageSize();
    if (pageNo != null && pageSize != null) {
      dictExample.setLimitRange((pageNo - 1) * pageSize + "," + pageSize);
    }

    List<Dict> dictList = dictMapper.selectByExampleWithBLOBs(dictExample);
    List<DictSearchResp> list = new ArrayList<>();
    for (Dict dict : dictList) {
      DictSearchResp dictSearchResp = new DictSearchResp();
      BeanUtils.copyProperties(dict, dictSearchResp);

      list.add(dictSearchResp);
    }

    DictSearchData dictSearchData = new DictSearchData();
    dictSearchData.setAllTotal(allTotal);
    dictSearchData.setCurrentTotal(list.size());
    dictSearchData.setList(list);

    return dictSearchData;
  }

  @Override
  public Boolean update(DictUpdate dictUpdate) {
    Dict dict = new Dict();
    BeanUtils.copyProperties(dictUpdate, dict);

    dict.setModifierId(userCode);

    return dictMapper.updateByPrimaryKeySelective(dict) > 0 ? true : false;
  }

  @Override
  public DictResp select(String title) {
    Dict dict = dictMapper.selectByPrimaryKey(title);
    if (dict == null) {
      return null;
    }

    DictResp dictResp = new DictResp();
    BeanUtils.copyProperties(dict, dictResp);

    return dictResp;
  }
}
