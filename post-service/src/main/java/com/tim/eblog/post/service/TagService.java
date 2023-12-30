package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.blog.TagBlogData;
import java.util.List;

public interface TagService {

  List<String> listTag();

  List<TagBlogData> queryTagBlog();
}
