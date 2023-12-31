package com.tim.eblog.post.vo.blog.tag;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：2023-12-30 14:30
 * @description：
 */
@Data
public class TagBlogData {

  private String tag;

  private List<TagBlog> tagBlogList;
}