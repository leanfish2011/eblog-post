package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.blog.ArchiveData;
import com.tim.eblog.post.vo.blog.ArchiveSumData;

public interface ArchiveService {

  ArchiveSumData selectArchiveSum();

  ArchiveData selectArchiveByYear(String year);
}
