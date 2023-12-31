package com.tim.eblog.post.dao;

import com.tim.eblog.post.po.Blog;
import com.tim.eblog.post.po.BlogExample;
import com.tim.eblog.post.vo.blog.archive.Archive;
import com.tim.eblog.post.vo.blog.archive.ArchiveSum;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper {

  int countByExample(BlogExample example);

  int deleteByExample(BlogExample example);

  int deleteByPrimaryKey(String id);

  int insert(Blog record);

  int insertSelective(Blog record);

  List<Blog> selectByExampleWithBLOBs(BlogExample example);

  List<Blog> selectByExample(BlogExample example);

  Blog selectByPrimaryKey(String id);

  int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

  int updateByExampleWithBLOBs(@Param("record") Blog record, @Param("example") BlogExample example);

  int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

  int updateByPrimaryKeySelective(Blog record);

  int updateByPrimaryKeyWithBLOBs(Blog record);

  int updateByPrimaryKey(Blog record);

  List<ArchiveSum> selectArchiveSum();

  List<Archive> selectArchiveByYear(@Param("year") String year);

  List<String> listTag();

}