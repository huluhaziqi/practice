DROP TABLE if EXISTS sys_users;
DROP TABLE if EXISTS sys_roles;
DROP TABLE if EXISTS sys_permissions;
DROP TABLE if EXISTS sys_roles_permissions;
DROP TABLE if EXISTS sys_users_roles;


create TABLE `sys_users`(
  `id` bigint(20) auto_increment,
  `username` VARCHAR(100) not NULL comment '账户名',
  `password` VARCHAR(100) NOT NULL comment '密码',
  `salt` VARCHAR(100)  comment '盐',
  `locked` bool DEFAULT FALSE ,
  PRIMARY KEY (`id`)
) charset=utf8 engine = InnoDB COLLATE=utf8_general_ci;

create TABLE `sys_roles`(
  `id` bigint(20) auto_increment,
  `role` VARCHAR(100) not NULL comment '角色',
  `description` VARCHAR(100) NOT NULL comment '描述',
  `available` bool DEFAULT FALSE comment '是否可用',
  PRIMARY KEY (`id`)
) charset=utf8 engine = InnoDB COLLATE=utf8_general_ci;

create TABLE `sys_permissions`(
  `id` bigint(20) auto_increment,
  `permission` VARCHAR(100) not NULL comment '权限',
  `description` VARCHAR(100) NOT NULL comment '描述',
  `available` bool DEFAULT FALSE comment '是否可用',
  PRIMARY KEY (`id`)
) charset=utf8 engine = InnoDB COLLATE=utf8_general_ci;

create TABLE `sys_users_roles`(
  `user_id` bigint(20) ,
  `role_id` bigint(20) ,
  UNIQUE  KEY `idx_user_role` (`user_id`, `role_id`)
) charset=utf8 engine = InnoDB COLLATE=utf8_general_ci;

create TABLE `sys_roles_permissions`(
  `role_id` bigint(20) ,
  `permission_id` bigint(20) ,
  UNIQUE  KEY `idx_role_permission` (`role_id`, `permission_id`)
) charset=utf8 engine = InnoDB COLLATE=utf8_general_ci;
