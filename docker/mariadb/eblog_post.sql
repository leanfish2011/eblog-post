-- eblog发布管理库
-- 1、创建库
drop database if exists eblog_post; -- 直接删除数据库，不提醒
create database eblog_post; -- 创建数据库
use eblog_post; -- 选择数据库

-- 2、创建表
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