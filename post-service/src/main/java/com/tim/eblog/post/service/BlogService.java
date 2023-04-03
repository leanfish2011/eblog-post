package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.blog.BlogAdd;
import com.tim.eblog.post.vo.blog.BlogResp;
import com.tim.eblog.post.vo.blog.BlogSearchData;
import com.tim.eblog.post.vo.blog.BlogUpdate;

public interface BlogService {

  BlogSearchData search(String input);

  Boolean add(BlogAdd blogAdd);

  Boolean delete(String id);

  Boolean update(BlogUpdate blogUpdate);

  BlogResp select(String id);

}
