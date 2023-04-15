package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.blog.ArchiveData;
import com.tim.eblog.post.vo.blog.ArchiveSumData;
import com.tim.eblog.post.vo.blog.BlogAdd;
import com.tim.eblog.post.vo.blog.BlogResp;
import com.tim.eblog.post.vo.blog.BlogSearchData;
import com.tim.eblog.post.vo.blog.BlogSearchReq;
import com.tim.eblog.post.vo.blog.BlogUpdate;

public interface BlogService {

  BlogSearchData search(BlogSearchReq blogSearchReq);

  Boolean add(BlogAdd blogAdd);

  Boolean delete(String id);

  Boolean update(BlogUpdate blogUpdate);

  BlogResp select(String id);

  ArchiveSumData selectArchiveSum();

  ArchiveData selectArchiveByYear(String year);

}
