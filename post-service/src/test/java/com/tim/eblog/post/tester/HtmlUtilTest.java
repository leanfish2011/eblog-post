package com.tim.eblog.post.tester;

import com.tim.eblog.post.util.HtmlUtil;
import org.junit.Test;

/**
 * @author：tim
 * @date：2023-04-09 12:08
 * @description：
 */
public class HtmlUtilTest {

  @Test
  public void htmlDeleteTest() {
    String htmlStr =
        "<script type>var i=1; alert(i)</script><style> .font1{font-size:12px}</style><span>少年中国说。</span>红日初升，其道大光。<h3>河出伏流，一泻汪洋。</h3>潜龙腾渊， 鳞爪&nbsp;&nbsp;飞扬。乳 虎啸  谷，百兽震惶。鹰隼试翼，风尘吸张。奇花初胎，矞矞皇皇。干将发硎，有作&nbsp其芒。天戴其苍，地履其黄。纵有千古，横有"
            +
            "八荒。<a href=\"www.baidu.com\">前途似海，来日方长</a>。<h1>美哉我少年中国，与天不老！</h1><p>壮哉我中国少年，与国无疆！</p>";

    System.out.println(HtmlUtil.delHtmlTags(htmlStr));
  }

}
