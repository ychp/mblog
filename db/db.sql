DROP TABLE IF EXISTS `user`;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `home_page` varchar(256) DEFAULT NULL COMMENT '主页',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '秘钥',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_nick_name` (`nick_name`),
  KEY `idx_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户表';


DROP TABLE IF EXISTS `role`;

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '角色表';

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  KEY `idx_user_role_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户角色表';

