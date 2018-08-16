# 一、描述
简易版论坛系统，第一版为个人博客形式<br>
主要功能有用户、分类、文章标签、文章、点赞、收藏、评价、说说、相册等功能<br>
辅助功能有数据分析(PV/UV等),黑名单(IP限制),友情链接<br>
后续可继续迭代，网站访问分析，访问行为分析，推文等功能

# 二、功能

## 1、用户
### 1.1、用户注册
用户填写基本信息进行注册，同一个ip十五分钟内重复注册，则需要填写校验码<br>
目前提供 用户名注册、邮箱注册
#### 1)、用户名注册
填写用户名、密码进行注册<br>
填写信息

* 用户名
* 密码

校验

1. 用户名校验（用户名重复，特殊字符）
2. 密码强度校验（不能与昵称一致）

#### 2)、邮箱注册（暂不支持）
用户填写邮箱获取密码设置链接，点开链接后，设置密码<br>
填写信息
* 邮箱
* 密码

### 1.2、用户登录
用户通过用户名(邮箱)密码登录，如果5分钟内失败3次以上，需要填写校验码<br>

填写信息

* 用户名（邮箱）
* 密码
* 校验码（非必填）

校验

1. 用户名校验
2. 密码校验

数据记录

* 记录每一次的登录ip以及时间

### 1.3、用户信息维护
用户维护基本信息，包括以下几项

* 昵称
* 邮箱
* 头像（默认通过 secure.gravatar.com 自动获取）
* 出生日期
* 故乡

## 2、分类
### 2.1、分类展示
展示文章分类<br>
字段展示

* id
* 名称

搜索条件

* 名称（全模糊查询）

### 2.2、分类创建
创建文章的分类，便于后续文章筛选管理<br>

填写字段

* 分类名称

校验

1. 名称重复校验

### 2.3、分类删除
删除不用的分类，如果存在文章关联，禁止删除

## 3、标签
### 3.1、标签列表
用于文章标记使用，便于用户快速搜索同类型文章<br>

展示

* 标签名称
* 使用次数
* 是否可见
* 操作
	* 显示时：隐藏
	* 隐藏式：显示

### 3.2、标签创建
创建文章标签<br>

填写内容

* 标签名称

校验

1. 是否重复校验

### 3.3、编辑是否可见
控制文章标签在文章编辑以及前台页面是否展示

### 3.4、删除标签
物理删除标签，同事删除所有文章与标签的关联关系

## 4、文章
### 4.1、文章列表 - 后台
用户后台管理文章<br>

展示字段

* 标题
* 分类
* 浏览/点赞
* 收藏/评价
* 权限
* 创建时间
* 操作
	* 权限为私有时：编辑，设为公开，删除
	* 权限为共有时：编辑，设为私有，删除

搜索条件

* 标题
* 创建时间（开始 ~ 结束）

### 4.2、新建文章
用户新建文章，用户可以选择富文本编辑器也可以选择markdown编辑器，默认富文本<br>

填写内容

* 标题
* 分类（下拉选择）
* 内容
* 标签（可以自定义，需要调用创建接口）
* 内容类型：1.富文本，2.markdown
* 文章权限：1.公开，0.私有

#### 1)富文本文章
使用富文本编辑器实现文章内容编辑

#### 2)markdown
使用markdown形式实现文章内容编辑<br>

后端需要生成文章内容简介，根据规则从文本中截取部分文字

### 4.3、编辑文章
用户编辑文章，根据文章内容类型，选择对应的编辑器渲染<br>

填写内容

* 标题
* 分类（下拉选择）
* 内容
* 标签（可以自定义，需要调用创建接口）

#### 1)富文本文章
使用富文本编辑器实现文章内容编辑

#### 2)markdown
使用markdown形式实现文章内容编辑<br>

后端需要生成文章内容简介，根据规则从文本中截取部分文字

### 4.4、编辑文章权限
设置文章权限为公开或私有

### 4.5、删除文章
删除文章（逻辑删除）
        
## 5、浏览量
前端固定请求后端数据采集接口，后端进行数据采集<br>

数据

* URL
* ip地址（如果路由后不能获取正确的ip地址等信息则需要传ip）

后端全局监听浏览量，监听api的请求记录（文章记录）<br>
前端控制页面数据采集（需要确认，通过前端路由后是否能够获取正确的ip地址）

## 6、收藏（TODO）
    - 文章收藏
        - 仅限登录用户

## 7、点赞（TODO）
    - 文章点赞
        - 相同ip或相同用户不重复记录
        
## 8、评论（TODO）
    - 文章评论
        - 无限级评论

## 8、说说（TODO）
    - 说说
        - 内容
        - 图片
        - 评论
        - 点赞
        - 时间戳

## 9、相册（TODO）
    - 相册
        - 标题，标签，图片数量，密码，是否可见
    - 照片
    

## 10、数据统计（TODO）
    - pv
    - uv
    - 有效用户率
    - 文章浏览量排行榜
    - 相册浏览量排行榜

## 11、配置（TODO）
### 11.1、友情链接管理
#### 1)列表
友情链接分页<br>

展示字段

* 网站名称
* 链接
* 是否展示
* 优先级
* 操作
	* 隐藏时：显示，删除
	* 显示时：隐藏，删除

全局操作<br>
优先级设置：页面展示所有友情链接，通过上下移动控制优先级，传给后端时，按照列表编号，从1开始，递增

#### 2)创建友情链接
添加友情链接，默认不展示<br>

填写内容

* 网站名称
* 链接

#### 3)编辑友情链接
编辑友情链接<br>

填写内容

* 网站名称
* 链接

#### 4)显示/隐藏
设置友情链接为显示或隐藏

#### 5)删除友情链接
物理删除友情链接

### 11.2、邮件(TODO)
    - 邮件
        - 配置
        - 发送，富文本
### 11.3、黑名单(TODO)
    - 黑名单
        - 恶意ip屏蔽
    


# 三、数据库设计

## 1、表设计

## 2、执行SQL
```sql
DROP TABLE IF EXISTS `sky_user`;

CREATE TABLE IF NOT EXISTS `sky_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(256) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '秘钥',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_name` (`name`),
  KEY `idx_user_nick_name` (`nick_name`),
  KEY `idx_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户表';

DROP TABLE IF EXISTS `sky_user_profile`;

CREATE TABLE IF NOT EXISTS `sky_user_profile` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `home_page` varchar(256) DEFAULT NULL COMMENT '主页',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `gender` varchar(8) DEFAULT NULL COMMENT '性别：male,female',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `country_id` int(9) DEFAULT NULL COMMENT '国家ID',
  `province_id` int(9) DEFAULT NULL COMMENT '省份ID',
  `city_id` int(9) DEFAULT NULL COMMENT '城市ID',
  `country` varchar(256) DEFAULT NULL COMMENT '国家',
  `province` varchar(256) DEFAULT NULL COMMENT '省份',
  `city` varchar(256) DEFAULT NULL COMMENT '城市',
  `synopsis` varchar(1024) DEFAULT NULL COMMENT '简介',
  `profile` varchar(512) DEFAULT NULL COMMENT '职业',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_profile_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

DROP TABLE IF EXISTS `sky_address`;

CREATE TABLE IF NOT EXISTS `sky_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `pinyin` varchar(128) DEFAULT NULL COMMENT '拼音',
  `english_name` varchar(128) DEFAULT NULL COMMENT '英文名',
  `unicode_code` varchar(256) DEFAULT NULL COMMENT 'ASCII码',
  PRIMARY KEY (`id`),
  KEY `idx_address_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '地址树表';

DROP TABLE IF EXISTS `sky_user_login_log`;

CREATE TABLE IF NOT EXISTS `sky_user_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `ip` varchar(256) NOT NULL COMMENT 'ip地址',
  `login_at` datetime NOT NULL,
  `logout_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_login_log_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户登录信息表';

DROP TABLE IF EXISTS `sky_category`;

CREATE TABLE IF NOT EXISTS `sky_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT '名称',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '分类表';

DROP TABLE IF EXISTS `sky_label`;

CREATE TABLE IF NOT EXISTS `sky_label` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT '名称',
  `visible` tinyint(1) NOT NULL COMMENT '是否可见，0.不可见，1.可见',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '标签表';

DROP TABLE IF EXISTS `sky_article`;

CREATE TABLE IF NOT EXISTS `sky_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) NOT NULL COMMENT '标题',
  `category_id` bigint(20) NOT NULL COMMENT '类目ID',
  `category_name` varchar(256) NOT NULL COMMENT '类目名称',
  `image` varchar(256) DEFAULT NULL COMMENT '预览图链接',
  `synopsis` varchar(512) DEFAULT NULL COMMENT '简介',
  `user_id` bigint(20) DEFAULT NULL COMMENT '作者Id',
  `author` varchar(256) DEFAULT NULL COMMENT '作者',
  `visible` tinyint(1) NOT NULL COMMENT '是否可见，0.不可见，1.可见',
  `deleted` tinyint(1) NOT NULL COMMENT '是否删除，0.未删除，1.已删除',
  `publish_at` datetime NOT NULL COMMENT '发布时间',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章表';

DROP TABLE IF EXISTS `sky_article_detail`;

CREATE TABLE IF NOT EXISTS `sky_article_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `is_markdown` tinyint(1) NOT NULL COMMENT '是否为markdown内容',
  `content` text COMMENT '内容',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_article_detail_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章详情表';

DROP TABLE IF EXISTS `sky_article_summary`;

CREATE TABLE IF NOT EXISTS `sky_article_summary` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `popular` int(20) DEFAULT 0 COMMENT '浏览量',
  `like` int(10) DEFAULT 0 COMMENT '点赞数',
  `favorite` int(10) DEFAULT 0 COMMENT '收藏数',
  `comments` int(10) DEFAULT 0 COMMENT '评论数量',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_article_summary_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章数据统计表';

DROP TABLE IF EXISTS `sky_article_label`;

CREATE TABLE IF NOT EXISTS `sky_article_label` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `label_id` bigint(20) NOT NULL COMMENT '标签ID',
  `label_name` varchar(256) NOT NULL COMMENT '标签',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_article_label_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章数据统计表';

DROP TABLE IF EXISTS `sky_friend_link`;

CREATE TABLE IF NOT EXISTS `sky_friend_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `web_name` VARCHAR(512) NOT NULL COMMENT '网站名称',
  `link` VARCHAR(512) NOT NULL COMMENT '链接',
  `visible` tinyint(1) NOT NULL COMMENT '是否可见，0.不可见，1.可见',
  `priority` int(10) DEFAULT 0 COMMENT '优先级',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '友情链接表';

DROP TABLE IF EXISTS `sky_ip_info`;

CREATE TABLE IF NOT EXISTS `sky_ip_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip地址',
  `country` varchar(128) DEFAULT NULL COMMENT '国家',
  `province` varchar(128) DEFAULT NULL COMMENT '省份',
  `city` varchar(128) DEFAULT NULL COMMENT '城市',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_ip_info_ip` (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ip信息表';

DROP TABLE IF EXISTS `sky_device_info`;

CREATE TABLE IF NOT EXISTS `sky_device_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `os` varchar(64) DEFAULT NULL COMMENT '系统',
  `browser` varchar(64) DEFAULT NULL COMMENT '浏览器',
  `browser_version` varchar(64) DEFAULT NULL COMMENT '浏览器版本',
  `device` varchar(64) DEFAULT NULL COMMENT '设备',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户设备信息表';

DROP TABLE IF EXISTS `sky_see_log`;

CREATE TABLE IF NOT EXISTS `sky_see_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip地址',
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `url` varchar(256) DEFAULT NULL COMMENT '访问页面',
  `uri` varchar(256) DEFAULT NULL COMMENT '请求',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问记录表';

DROP TABLE IF EXISTS `sky_like_log`;

CREATE TABLE IF NOT EXISTS `sky_like_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(4) DEFAULT NULL COMMENT '类型:1.文章,2.说说,3.照片',
  `aim_id` bigint(20) DEFAULT NULL COMMENT '目标id',
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip地址',
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `url` varchar(256) DEFAULT NULL COMMENT '访问页面',
  `uri` varchar(256) DEFAULT NULL COMMENT '请求',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞记录表';
```
    
