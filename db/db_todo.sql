DROP TABLE IF EXISTS `sky_role`;

CREATE TABLE IF NOT EXISTS `sky_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '角色表';

DROP TABLE IF EXISTS `sky_user_role`;

CREATE TABLE IF NOT EXISTS `sky_user_role` (
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  KEY `idx_user_role_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户角色表';

DROP TABLE IF EXISTS `sky_config`;

CREATE TABLE IF NOT EXISTS `sky_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(128) DEFAULT NULL COMMENT 'key',
  `value` text DEFAULT NULL COMMENT '内容',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_config_key` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '配置表';