package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.blog.tag.TagBlogData;
import java.util.List;

public interface TagService {

  List<String> listTag();

  List<TagBlogData> queryTagBlog();
}
