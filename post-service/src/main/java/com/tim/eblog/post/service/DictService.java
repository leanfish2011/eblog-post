package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.dict.DictItem;
import com.tim.eblog.post.vo.dict.DictResp;
import com.tim.eblog.post.vo.dict.DictSearchData;
import com.tim.eblog.post.vo.dict.DictSearchReq;
import com.tim.eblog.post.vo.dict.DictUpdate;
import java.util.List;

public interface DictService {

  List<DictItem> listDictItem();

  DictSearchData search(DictSearchReq dictSearchReq);

  Boolean update(DictUpdate dictUpdate);

  DictResp select(String title);
}