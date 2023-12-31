package com.tim.eblog.post.service;

import com.tim.eblog.post.vo.blog.archive.ArchiveData;
import com.tim.eblog.post.vo.blog.archive.ArchiveSumData;

public interface ArchiveService {

  ArchiveSumData selectArchiveSum();

  ArchiveData selectArchiveByYear(String year);
}
