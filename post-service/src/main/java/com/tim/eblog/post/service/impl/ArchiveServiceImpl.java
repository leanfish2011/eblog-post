package com.tim.eblog.post.service.impl;

import com.tim.eblog.post.dao.BlogMapper;
import com.tim.eblog.post.service.ArchiveService;
import com.tim.eblog.post.vo.blog.archive.Archive;
import com.tim.eblog.post.vo.blog.archive.ArchiveData;
import com.tim.eblog.post.vo.blog.archive.ArchiveSum;
import com.tim.eblog.post.vo.blog.archive.ArchiveSumData;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date：2023-12-30 14:33
 * @description：
 */
@Service
@Slf4j
public class ArchiveServiceImpl implements ArchiveService {

  @Autowired
  private BlogMapper blogMapper;

  @Override
  public ArchiveSumData selectArchiveSum() {
    List<ArchiveSum> archiveSumList = blogMapper.selectArchiveSum();
    ArchiveSumData archiveSumData = new ArchiveSumData();
    archiveSumData.setArchiveSumList(archiveSumList);

    return archiveSumData;
  }

  @Override
  public ArchiveData selectArchiveByYear(String year) {
    List<Archive> archiveList = blogMapper.selectArchiveByYear(year);
    ArchiveData archiveData = new ArchiveData();
    archiveData.setArchiveList(archiveList);

    return archiveData;
  }

}
