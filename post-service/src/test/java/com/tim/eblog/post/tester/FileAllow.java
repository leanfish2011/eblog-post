package com.tim.eblog.post.tester;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author：tim
 * @date：2023-04-06 15:39
 * @description：
 */
public class FileAllow {

  private boolean isAllow(String fileName) {
    String[] allowFiles = {"gif", "png", "jpg", "jpeg", "bpm", "svg"};
    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
    List<String> suffixList = Arrays.stream(allowFiles).collect(Collectors.toList());

    return suffixList.contains(suffix);
  }

  @Test
  public void testIsAllow() {
    String fileName = "me.png";
    Assert.assertTrue(isAllow(fileName));
  }

}
