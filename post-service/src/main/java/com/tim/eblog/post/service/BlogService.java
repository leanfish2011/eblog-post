package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.BlogAdd;
import com.tim.eblog.post.vo.BlogResp;
import com.tim.eblog.post.vo.BlogSearchData;
import com.tim.eblog.post.vo.BlogUpdate;

public interface BlogService {

  BlogSearchData search(String input);

  Boolean add(BlogAdd blogAdd);

  Boolean delete(String id);

  Boolean update(BlogUpdate blogUpdate);

  BlogResp select(String id);

}
