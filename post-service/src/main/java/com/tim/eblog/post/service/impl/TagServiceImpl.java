package com.tim.eblog.post.service.impl;

import com.tim.eblog.post.dao.BlogMapper;
import com.tim.eblog.post.po.Blog;
import com.tim.eblog.post.service.TagService;
import com.tim.eblog.post.vo.blog.TagBlog;
import com.tim.eblog.post.vo.blog.TagBlogData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date：2023-12-30 14:02
 * @description：
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {

  @Autowired
  private BlogMapper blogMapper;

  @Override
  public List<String> listTag() {
    List<String> originTags = blogMapper.listTag();
    Set<String> tagResult = new HashSet<>();
    String[] itemSplit;
    for (String item : originTags) {
      if (item.contains(",")) {
        itemSplit = item.split(",");
        for (String elment : itemSplit) {
          tagResult.add(elment);
        }
      } else {
        tagResult.add(item);
      }
    }

    if (!tagResult.isEmpty()) {
      return new ArrayList<>(tagResult);
    }

    return null;
  }

  @Override
  public List<TagBlogData> queryTagBlog() {
    List<String> tags = this.listTag();
    List<Blog> blogList = blogMapper.selectByExample(null);
    List<TagBlogData> tagBlogDataList = new ArrayList<>();
    for (String tag : tags) {
      TagBlogData tagBlogData = new TagBlogData();
      tagBlogData.setTag(tag);

      List<TagBlog> tagBlogs = blogList.stream().filter(blog -> blog.getTag().contains(tag))
          .map(blog -> {
            TagBlog tagBlog = new TagBlog();
            tagBlog.setId(blog.getId());
            tagBlog.setTitle(blog.getTitle());
            tagBlog.setCreateTime(blog.getCreateTime());

            return tagBlog;
          }).collect(Collectors.toList());

      tagBlogData.setTagBlogList(tagBlogs);
      tagBlogDataList.add(tagBlogData);
    }

    return tagBlogDataList;
  }
}
