package com.tim.eblog.post.service.impl;

import com.tim.eblog.post.dao.BlogMapper;
import com.tim.eblog.post.po.Blog;
import com.tim.eblog.post.po.BlogExample;
import com.tim.eblog.post.po.BlogExample.Criteria;
import com.tim.eblog.post.service.BlogService;
import com.tim.eblog.post.util.HtmlUtil;
import com.tim.eblog.post.vo.blog.BlogAdd;
import com.tim.eblog.post.vo.blog.BlogResp;
import com.tim.eblog.post.vo.blog.BlogSearchData;
import com.tim.eblog.post.vo.blog.BlogSearchResp;
import com.tim.eblog.post.vo.blog.BlogUpdate;
import java.util.ArrayList;
import java.util.Arrays;
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

  @Value("${login.admin.code:admin}")
  private String userCode;

  @Autowired
  private BlogMapper blogMapper;

  @Override
  public BlogSearchData search(String title) {
    BlogExample blogExample = new BlogExample();
    Criteria criteria = blogExample.createCriteria();

    if (!StringUtils.isEmpty(title)) {
      criteria.andTitleLike("%" + title + "%");
    }

    blogExample.setOrderByClause(" create_time desc");

    List<Blog> blogList = blogMapper.selectByExample(blogExample);
    List<BlogSearchResp> list = new ArrayList<>();
    for (Blog blog : blogList) {
      BlogSearchResp blogSearchResp = new BlogSearchResp();
      BeanUtils.copyProperties(blog, blogSearchResp);

      blogSearchResp.setArrayTag(getTagArray(blog.getTag()));

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

    blog.setTag(getTagStr(blogAdd.getArrayTag()));
    blog.setId(UUID.randomUUID().toString());
    blog.setCreatorId(userCode);
    blog.setRemark(HtmlUtil.delHtmlTags(blogAdd.getContent()));
    blog.setRemark(getRemarkTip(blog.getRemark()));

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

    blog.setTag(getTagStr(blogUpdate.getArrayTag()));
    blog.setModifierId(userCode);
    blog.setRemark(HtmlUtil.delHtmlTags(blogUpdate.getContent()));
    blog.setRemark(getRemarkTip(blog.getRemark()));

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

    blogResp.setArrayTag(getTagArray(blog.getTag()));

    return blogResp;
  }

  private String getRemarkTip(String remark) {
    return remark.length() > 255 ? remark.substring(0, 255) : remark;
  }

  private String getTagStr(String[] arrayTag) {
    return StringUtils.collectionToDelimitedString(Arrays.asList(arrayTag), ",");
  }

  private String[] getTagArray(String tag) {
    return StringUtils.delimitedListToStringArray(tag, ",");
  }

}
