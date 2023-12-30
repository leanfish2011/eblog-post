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
INSERT INTO `eblog_post`.`dict` (`id`,`creator_id`,`title`,`content`,`remark`) VALUES ('2','admin','aboutMe',"<p>爱好文学</p><p>爱好跑步</p>",'关于');
