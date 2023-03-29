package com.tim.eblog.post.service.impl;

import com.tim.eblog.post.dao.BlogMapper;
import com.tim.eblog.post.po.Blog;
import com.tim.eblog.post.po.BlogExample;
import com.tim.eblog.post.po.BlogExample.Criteria;
import com.tim.eblog.post.service.BlogService;
import com.tim.eblog.post.vo.BlogAdd;
import com.tim.eblog.post.vo.BlogResp;
import com.tim.eblog.post.vo.BlogSearchData;
import com.tim.eblog.post.vo.BlogSearchResp;
import com.tim.eblog.post.vo.BlogUpdate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author：tim
 * @date：20-5-23 下午5:42
 * @description：
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

  @Value("${login.admin.username:admin}")
  private String username;

  @Autowired
  private BlogMapper blogMapper;

  @Override
  public BlogSearchData search(String input) {
    BlogExample blogExample = new BlogExample();
    Criteria criteria = blogExample.createCriteria();

    if (!StringUtils.isEmpty(input)) {
      criteria.andTitleLike("%" + input + "%");
    }

    blogExample.setOrderByClause(" create_time desc");

    List<Blog> blogList = blogMapper.selectByExample(blogExample);
    List<BlogSearchResp> list = new ArrayList<>();
    for (Blog blog : blogList) {
      BlogSearchResp blogSearchResp = new BlogSearchResp();
      BeanUtils.copyProperties(blog, blogSearchResp);

      list.add(blogSearchResp);
    }

    int allTotal = blogMapper.countByExample(blogExample);

    BlogSearchData blogSearchData = new BlogSearchData();
    blogSearchData.setAllTotal(allTotal);
    blogSearchData.setCurrentTotal(list.size());
    blogSearchData.setList(list);

    return blogSearchData;
  }

  @Override
  public Boolean add(BlogAdd blogAdd) {
    Blog blog = new Blog();
    BeanUtils.copyProperties(blogAdd, blog);
    blog.setId(UUID.randomUUID().toString());
    blog.setCreatorId(username);

    return blogMapper.insertSelective(blog) > 0 ? true : false;
  }

  @Override
  public Boolean delete(String id) {
    return blogMapper.deleteByPrimaryKey(id) > 0 ? true : false;
  }

  @Override
  public Boolean update(BlogUpdate blogUpdate) {
    Blog blog = new Blog();
    BeanUtils.copyProperties(blogUpdate, blog);
    blog.setModifierId(username);

    return blogMapper.updateByPrimaryKeySelective(blog) > 0 ? true : false;

  }

  @Override
  public BlogResp select(String id) {
    Blog blog = blogMapper.selectByPrimaryKey(id);
    if (blog == null) {
      return null;
    }

    BlogResp blogResp = new BlogResp();
    BeanUtils.copyProperties(blog, blogResp);

    return blogResp;
  }
}
