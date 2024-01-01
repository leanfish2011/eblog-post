-- eblog发布管理库
-- 1、创建库
drop database if exists eblog_post; -- 直接删除数据库，不提醒
create database eblog_post; -- 创建数据库
use eblog_post; -- 选择数据库

-- 2、创建表-博客
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
    `id` varchar(60) NOT NULL COMMENT '主键',
    `create_time` datetime DEFAULT current_timestamp() COMMENT '创建时间',
    `creator_id` varchar(60) NOT NULL COMMENT '创建人id',
    `modified_time` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '修改时间',
    `modifier_id` varchar(60) DEFAULT NULL COMMENT '修改人id',
    `title` varchar(100) DEFAULT NULL COMMENT '博客标题',
    `content` mediumtext DEFAULT NULL COMMENT '博客内容',
    `read_count` int(11) DEFAULT 0 COMMENT '博客被阅读次数',
    `remark` varchar(255) DEFAULT NULL COMMENT '博客摘要',
    `tag` varchar(100) DEFAULT NULL COMMENT '标签',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客';

-- 3、创建表-数据字典
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
    `id` varchar(60) NOT NULL COMMENT '主键',
    `create_time` datetime DEFAULT current_timestamp() COMMENT '创建时间',
    `creator_id` varchar(60) NOT NULL COMMENT '创建人id',
    `modified_time` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '修改时间',
    `modifier_id` varchar(60) DEFAULT NULL COMMENT '修改人id',
    `title` varchar(100) NOT NULL COMMENT '标题',
    `content` mediumtext DEFAULT NULL COMMENT '内容',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- 初始化数据
INSERT INTO `eblog_post`.`blog` (`id`,`creator_id`,`title`,`content`,`remark`,`tag`) VALUES ('0','admin','这是第一篇博客','<p>这是第一篇博客，开篇</p>','这是第一篇博客，开篇','生活');

INSERT INTO `eblog_post`.`dict` (`id`,`creator_id`,`title`,`content`,`remark`) VALUES ('0','admin','blogName',"Tim's Blog",'博客标题');
INSERT INTO `eblog_post`.`dict` (`id`,`creator_id`,`title`,`content`,`remark`) VALUES ('1','admin','copyRight',"Copyright © Tim 2016-",'版权申明');
INSERT INTO `eblog_post`.`dict` (`id`,`creator_id`,`title`,`content`,`remark`) VALUES ('2','admin','aboutMe',"<h4><strong>简介：</strong></h4>\n\n<ul>\n	<li>\n	<p>技术爱好者，喜欢折腾各种技术。从前端到后端，深度学习等都有所研究。</p>\n	</li>\n	<li>\n	<p>文学爱好者，喜欢读各种书，尤其是中国古典文学。也喜欢写一些文章。</p>\n	</li>\n</ul>\n\n<h4><strong>联系我：</strong></h4>\n\n<ul>\n	<li>\n	<p><img alt=\"\" height=\"20\" src=\"../../../../../static/img/email.jpg\" width=\"20\" />邮箱：leanfish2011@163.com</p>\n	</li>\n	<li>\n	<p><img alt=\"\" height=\"20\" src=\"../../../../../static/img/weixin.png\" width=\"20\" />微信公众号：云山闲人</p>\n	<img alt=\"\" height=\"174\" src=\"../../../../../static/img/weixinsite.jpeg\" width=\"157\" /></li>\n	<li>\n	<p><img alt=\"\" height=\"20\" src=\"../../../../../static/img/weibo.png\" width=\"20\" /> 微博：<a href=\"http://weibo.com/u/1718711654\" target=\"_blank\">闯荡南北的鱼</a></p>\n	</li>\n	<li>\n	<p><img alt=\"\" height=\"20\" src=\"../../../../../static/img/blog.jpg\" width=\"20\" /> 博客：<a href=\"https://www.cnblogs.com/leanfish\" target=\"_blank\">瘦鱼-博客园</a></p>\n	</li>\n	<li>\n	<p><img alt=\"\" height=\"20\" src=\"../../../../../static/img/jian.png\" width=\"20\" /> 简书：<a href=\"https://www.jianshu.com/u/bff8cc0b4914\" target=\"_blank\">瘦鱼-简书</a></p>\n	</li>\n	<li>\n	<p><img alt=\"\" height=\"20\" src=\"../../../../../static/img/zhi.png\" width=\"20\" /> 知乎：<a href=\"https://www.zhihu.com/people/feige2011\" target=\"_blank\">蒙蒙残雨-知乎</a></p>\n	</li>\n	<li>\n	<p><img alt=\"\" height=\"18\" src=\"../../../../../static/img/github.png\" width=\"18\" /> github：<a href=\"https://github.com/leanfish2011/\" target=\"_blank\">leanfish2011</a></p>\n	</li>\n</ul>",'关于');
