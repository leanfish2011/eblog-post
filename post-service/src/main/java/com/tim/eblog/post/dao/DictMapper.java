package com.tim.eblog.post.dao;

import com.tim.eblog.post.po.Dict;
import com.tim.eblog.post.po.DictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictMapper {

  int countByExample(DictExample example);

  int deleteByExample(DictExample example);

  int deleteByPrimaryKey(String title);

  int insert(Dict record);

  int insertSelective(Dict record);

  List<Dict> selectByExampleWithBLOBs(DictExample example);

  List<Dict> selectByExample(DictExample example);

  Dict selectByPrimaryKey(String title);

  int updateByExampleSelective(@Param("record") Dict record, @Param("example") DictExample example);

  int updateByExampleWithBLOBs(@Param("record") Dict record, @Param("example") DictExample example);

  int updateByExample(@Param("record") Dict record, @Param("example") DictExample example);

  int updateByPrimaryKeySelective(Dict record);

  int updateByPrimaryKeyWithBLOBs(Dict record);

  int updateByPrimaryKey(Dict record);
}