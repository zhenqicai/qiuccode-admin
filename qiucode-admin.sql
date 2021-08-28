/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : qiucode-admin

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 22/08/2021 10:43:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qiu_admin
-- ----------------------------
DROP TABLE IF EXISTS `qiu_admin`;
CREATE TABLE `qiu_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户登录账号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码加密盐值',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 0锁定 1有效',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qiu_admin
-- ----------------------------
INSERT INTO `qiu_admin` VALUES (1, 'admin', '3980e4044675f6339248ee0c735c7d72', '123456', 1, 'admin@qiucode.cn', 'cnrhVkzwxjPwAaCfPbdc.png');
INSERT INTO `qiu_admin` VALUES (2, 'zhangsan', '64b710a3927b0d69c386b9dbbb2259c8', '8NtuF8u', 1, 'zhangsan@qiucode.cn', 'cnrhVkzwxjPwAaCfPbdc.png');

-- ----------------------------
-- Table structure for qiu_menu
-- ----------------------------
DROP TABLE IF EXISTS `qiu_menu`;
CREATE TABLE `qiu_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限标识',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
  `sort` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `qiu_menu_parenqiu_id`(`parent_id`) USING BTREE,
  INDEX `qiu_menu_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qiu_menu
-- ----------------------------
INSERT INTO `qiu_menu` VALUES (1, 0, '系统管理', NULL, NULL, 'layui-icon-setting', '0', 1, '2017-12-27 16:39:07', NULL);
INSERT INTO `qiu_menu` VALUES (2, 0, '系统监控', '', '', 'layui-icon-alert', '0', 2, '2017-12-27 16:45:51', '2019-06-13 11:20:40');
INSERT INTO `qiu_menu` VALUES (3, 1, '用户管理', '/system/user', 'user:view', '', '0', 1, '2017-12-27 16:47:13', '2019-12-04 16:46:50');
INSERT INTO `qiu_menu` VALUES (4, 1, '角色管理', '/system/role', 'role:view', '', '0', 2, '2017-12-27 16:48:09', '2019-06-13 08:57:19');
INSERT INTO `qiu_menu` VALUES (5, 1, '菜单管理', '/system/menu', 'menu:view', '', '0', 3, '2017-12-27 16:48:57', '2019-06-13 08:57:34');
INSERT INTO `qiu_menu` VALUES (6, 1, '部门管理', '/system/dept', 'dept:view', '', '0', 4, '2017-12-27 16:57:33', '2019-06-14 19:56:00');
INSERT INTO `qiu_menu` VALUES (8, 2, '在线用户', '/monitor/online', 'online:view', '', '0', 1, '2017-12-27 16:59:33', '2019-06-13 14:30:31');
INSERT INTO `qiu_menu` VALUES (10, 2, '系统日志', '/monitor/log', 'log:view', '', '0', 2, '2017-12-27 17:00:50', '2019-06-13 14:30:37');
INSERT INTO `qiu_menu` VALUES (11, 3, '新增用户', NULL, 'user:add', NULL, '1', NULL, '2017-12-27 17:02:58', NULL);
INSERT INTO `qiu_menu` VALUES (12, 3, '修改用户', NULL, 'user:update', NULL, '1', NULL, '2017-12-27 17:04:07', NULL);
INSERT INTO `qiu_menu` VALUES (13, 3, '删除用户', NULL, 'user:delete', NULL, '1', NULL, '2017-12-27 17:04:58', NULL);
INSERT INTO `qiu_menu` VALUES (14, 4, '新增角色', NULL, 'role:add', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `qiu_menu` VALUES (15, 4, '修改角色', NULL, 'role:update', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `qiu_menu` VALUES (16, 4, '删除角色', NULL, 'role:delete', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `qiu_menu` VALUES (17, 5, '新增菜单', NULL, 'menu:add', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `qiu_menu` VALUES (18, 5, '修改菜单', NULL, 'menu:update', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `qiu_menu` VALUES (19, 5, '删除菜单', NULL, 'menu:delete', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `qiu_menu` VALUES (20, 6, '新增部门', NULL, 'dept:add', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `qiu_menu` VALUES (21, 6, '修改部门', NULL, 'dept:update', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `qiu_menu` VALUES (22, 6, '删除部门', NULL, 'dept:delete', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `qiu_menu` VALUES (23, 8, '踢出用户', NULL, 'user:kickout', NULL, '1', NULL, '2017-12-27 17:11:13', NULL);
INSERT INTO `qiu_menu` VALUES (24, 10, '删除日志', NULL, 'log:delete', NULL, '1', NULL, '2017-12-27 17:11:45', '2019-06-06 05:56:40');
INSERT INTO `qiu_menu` VALUES (101, 0, '任务调度', NULL, NULL, 'layui-icon-time-circle', '0', 3, '2018-02-24 15:52:57', NULL);
INSERT INTO `qiu_menu` VALUES (102, 101, '定时任务', '/job/job', 'job:view', '', '0', 1, '2018-02-24 15:53:53', '2018-04-25 09:05:12');
INSERT INTO `qiu_menu` VALUES (103, 102, '新增任务', NULL, 'job:add', NULL, '1', NULL, '2018-02-24 15:55:10', NULL);
INSERT INTO `qiu_menu` VALUES (104, 102, '修改任务', NULL, 'job:update', NULL, '1', NULL, '2018-02-24 15:55:53', NULL);
INSERT INTO `qiu_menu` VALUES (105, 102, '删除任务', NULL, 'job:delete', NULL, '1', NULL, '2018-02-24 15:56:18', NULL);
INSERT INTO `qiu_menu` VALUES (106, 102, '暂停任务', NULL, 'job:pause', NULL, '1', NULL, '2018-02-24 15:57:08', NULL);
INSERT INTO `qiu_menu` VALUES (107, 102, '恢复任务', NULL, 'job:resume', NULL, '1', NULL, '2018-02-24 15:58:21', NULL);
INSERT INTO `qiu_menu` VALUES (108, 102, '立即执行任务', NULL, 'job:run', NULL, '1', NULL, '2018-02-24 15:59:45', NULL);
INSERT INTO `qiu_menu` VALUES (109, 101, '调度日志', '/job/log', 'job:log:view', '', '0', 2, '2018-02-24 16:00:45', '2019-06-09 02:48:19');
INSERT INTO `qiu_menu` VALUES (110, 109, '删除日志', NULL, 'job:log:delete', NULL, '1', NULL, '2018-02-24 16:01:21', NULL);
INSERT INTO `qiu_menu` VALUES (115, 0, '其他模块', NULL, NULL, 'layui-icon-gift', '0', 5, '2019-05-27 10:18:07', NULL);
INSERT INTO `qiu_menu` VALUES (116, 115, 'Apex图表', '', '', NULL, '0', 2, '2019-05-27 10:21:35', NULL);
INSERT INTO `qiu_menu` VALUES (117, 116, '线性图表', '/others/apex/line', 'apex:line:view', NULL, '0', 1, '2019-05-27 10:24:49', NULL);
INSERT INTO `qiu_menu` VALUES (118, 115, '高德地图', '/others/map', 'map:view', '', '0', 3, '2019-05-27 17:13:12', '2019-06-12 15:33:00');
INSERT INTO `qiu_menu` VALUES (119, 116, '面积图表', '/others/apex/area', 'apex:area:view', NULL, '0', 2, '2019-05-27 18:49:22', NULL);
INSERT INTO `qiu_menu` VALUES (120, 116, '柱形图表', '/others/apex/column', 'apex:column:view', NULL, '0', 3, '2019-05-27 18:51:33', NULL);
INSERT INTO `qiu_menu` VALUES (121, 116, '雷达图表', '/others/apex/radar', 'apex:radar:view', NULL, '0', 4, '2019-05-27 18:56:05', NULL);
INSERT INTO `qiu_menu` VALUES (122, 116, '条形图表', '/others/apex/bar', 'apex:bar:view', NULL, '0', 5, '2019-05-27 18:57:02', NULL);
INSERT INTO `qiu_menu` VALUES (123, 116, '混合图表', '/others/apex/mix', 'apex:mix:view', '', '0', 6, '2019-05-27 18:58:04', '2019-06-06 02:55:23');
INSERT INTO `qiu_menu` VALUES (125, 115, '导入导出', '/others/eximport', 'others:eximport:view', '', '0', 4, '2019-05-27 19:01:57', '2019-06-13 01:20:14');
INSERT INTO `qiu_menu` VALUES (126, 132, '系统图标', '/others/qiu/icon', 'qiu:icons:view', '', '0', 4, '2019-05-27 19:03:18', '2019-06-06 03:05:26');
INSERT INTO `qiu_menu` VALUES (127, 2, '请求追踪', '/monitor/httptrace', 'httptrace:view', '', '0', 6, '2019-05-27 19:06:38', '2019-06-13 14:36:43');
INSERT INTO `qiu_menu` VALUES (128, 2, '系统信息', NULL, NULL, NULL, '0', 7, '2019-05-27 19:08:23', NULL);
INSERT INTO `qiu_menu` VALUES (129, 128, 'JVM信息', '/monitor/jvm', 'jvm:view', '', '0', 1, '2019-05-27 19:08:50', '2019-06-13 14:36:51');
INSERT INTO `qiu_menu` VALUES (131, 128, '服务器信息', '/monitor/server', 'server:view', '', '0', 3, '2019-05-27 19:10:07', '2019-06-13 14:37:04');
INSERT INTO `qiu_menu` VALUES (132, 115, '组件', '', '', NULL, '0', 1, '2019-05-27 19:13:54', NULL);
INSERT INTO `qiu_menu` VALUES (133, 132, '表单组件', '/others/qiu/form', 'qiu:form:view', NULL, '0', 1, '2019-05-27 19:14:45', NULL);
INSERT INTO `qiu_menu` VALUES (134, 132, '工具', '/others/qiu/tools', 'qiu:tools:view', '', '0', 3, '2019-05-29 10:11:22', '2019-06-12 13:21:27');
INSERT INTO `qiu_menu` VALUES (135, 132, '表单组合', '/others/qiu/form/group', 'qiu:formgroup:view', NULL, '0', 2, '2019-05-29 10:16:19', NULL);
INSERT INTO `qiu_menu` VALUES (136, 2, '登录日志', '/monitor/loginlog', 'loginlog:view', '', '0', 3, '2019-05-29 15:56:15', '2019-06-13 14:35:56');
INSERT INTO `qiu_menu` VALUES (137, 0, '代码生成', '', NULL, 'layui-icon-verticalright', '0', 4, '2019-06-03 15:35:58', NULL);
INSERT INTO `qiu_menu` VALUES (138, 137, '生成配置', '/generator/configure', 'generator:configure:view', NULL, '0', 1, '2019-06-03 15:38:36', NULL);
INSERT INTO `qiu_menu` VALUES (139, 137, '代码生成', '/generator/generator', 'generator:view', '', '0', 2, '2019-06-03 15:39:15', '2019-06-13 14:31:38');
INSERT INTO `qiu_menu` VALUES (159, 132, '其他组件', '/others/qiu/others', 'others:qiu:others', '', '0', 5, '2019-06-12 07:51:08', '2019-06-12 07:51:40');
INSERT INTO `qiu_menu` VALUES (160, 3, '密码重置', NULL, 'user:password:reset', NULL, '1', NULL, '2019-06-13 08:40:13', NULL);
INSERT INTO `qiu_menu` VALUES (161, 3, '导出Excel', NULL, 'user:export', NULL, '1', NULL, '2019-06-13 08:40:34', NULL);
INSERT INTO `qiu_menu` VALUES (162, 4, '导出Excel', NULL, 'role:export', NULL, '1', NULL, '2019-06-13 14:29:00', '2019-06-13 14:29:11');
INSERT INTO `qiu_menu` VALUES (163, 5, '导出Excel', NULL, 'menu:export', NULL, '1', NULL, '2019-06-13 14:29:32', NULL);
INSERT INTO `qiu_menu` VALUES (164, 6, '导出Excel', NULL, 'dept:export', NULL, '1', NULL, '2019-06-13 14:29:59', NULL);
INSERT INTO `qiu_menu` VALUES (165, 138, '修改配置', NULL, 'generator:configure:update', NULL, '1', NULL, '2019-06-13 14:32:09', '2019-06-13 14:32:20');
INSERT INTO `qiu_menu` VALUES (166, 139, '生成代码', NULL, 'generator:generate', NULL, '1', NULL, '2019-06-13 14:32:51', NULL);
INSERT INTO `qiu_menu` VALUES (167, 125, '模板下载', NULL, 'eximport:template', NULL, '1', NULL, '2019-06-13 14:33:37', NULL);
INSERT INTO `qiu_menu` VALUES (168, 125, '导出Excel', NULL, 'eximport:export', NULL, '1', NULL, '2019-06-13 14:33:57', NULL);
INSERT INTO `qiu_menu` VALUES (169, 125, '导入Excel', NULL, 'eximport:import', NULL, '1', NULL, '2019-06-13 14:34:19', NULL);
INSERT INTO `qiu_menu` VALUES (170, 10, '导出Excel', NULL, 'log:export', NULL, '1', NULL, '2019-06-13 14:34:55', NULL);
INSERT INTO `qiu_menu` VALUES (171, 136, '删除日志', NULL, 'loginlog:delete', NULL, '1', NULL, '2019-06-13 14:35:27', '2019-06-13 14:36:08');
INSERT INTO `qiu_menu` VALUES (172, 136, '导出Excel', NULL, 'loginlog:export', NULL, '1', NULL, '2019-06-13 14:36:26', NULL);
INSERT INTO `qiu_menu` VALUES (173, 102, '导出Excel', NULL, 'job:export', NULL, '1', NULL, '2019-06-13 14:37:25', NULL);
INSERT INTO `qiu_menu` VALUES (174, 109, '导出Excel', NULL, 'job:log:export', NULL, '1', NULL, '2019-06-13 14:37:46', '2019-06-13 14:38:02');
INSERT INTO `qiu_menu` VALUES (175, 2, 'Swagger文档', '/monitor/swagger', 'swagger:view', '', '0', 8, '2019-08-18 17:25:36', '2019-08-18 17:25:59');
INSERT INTO `qiu_menu` VALUES (178, 115, '数据权限', '/others/datapermission', 'others:datapermission', '', '0', 5, '2020-04-29 09:34:25', NULL);

-- ----------------------------
-- Table structure for qiu_role
-- ----------------------------
DROP TABLE IF EXISTS `qiu_role`;
CREATE TABLE `qiu_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qiu_role
-- ----------------------------
INSERT INTO `qiu_role` VALUES (1, '系统管理员', '系统管理员，拥有所有操作权限 ^_^', '2019-06-14 16:23:11', '2019-08-18 17:26:19');
INSERT INTO `qiu_role` VALUES (2, '注册账户', '注册账户，拥有查看，新增权限（新增用户除外）和导出Excel权限', '2019-06-14 16:00:15', '2019-08-18 17:36:02');
INSERT INTO `qiu_role` VALUES (77, 'Redis监控员', '负责Redis模块', '2019-06-14 20:49:22', NULL);
INSERT INTO `qiu_role` VALUES (78, '系统监控员', '负责整个系统监控模块', '2019-06-14 20:50:07', NULL);
INSERT INTO `qiu_role` VALUES (79, '跑批人员', '负责任务调度跑批模块', '2019-06-14 20:51:02', NULL);
INSERT INTO `qiu_role` VALUES (80, '开发人员', '拥有代码生成模块的权限', '2019-06-14 20:51:26', NULL);

-- ----------------------------
-- Table structure for qiu_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `qiu_role_menu`;
CREATE TABLE `qiu_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单/按钮ID',
  INDEX `qiu_role_menu_menu_id`(`menu_id`) USING BTREE,
  INDEX `qiu_role_menu_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qiu_role_menu
-- ----------------------------
INSERT INTO `qiu_role_menu` VALUES (77, 2);
INSERT INTO `qiu_role_menu` VALUES (78, 2);
INSERT INTO `qiu_role_menu` VALUES (78, 8);
INSERT INTO `qiu_role_menu` VALUES (78, 23);
INSERT INTO `qiu_role_menu` VALUES (78, 10);
INSERT INTO `qiu_role_menu` VALUES (78, 24);
INSERT INTO `qiu_role_menu` VALUES (78, 170);
INSERT INTO `qiu_role_menu` VALUES (78, 136);
INSERT INTO `qiu_role_menu` VALUES (78, 171);
INSERT INTO `qiu_role_menu` VALUES (78, 172);
INSERT INTO `qiu_role_menu` VALUES (78, 127);
INSERT INTO `qiu_role_menu` VALUES (78, 128);
INSERT INTO `qiu_role_menu` VALUES (78, 129);
INSERT INTO `qiu_role_menu` VALUES (78, 131);
INSERT INTO `qiu_role_menu` VALUES (79, 101);
INSERT INTO `qiu_role_menu` VALUES (79, 102);
INSERT INTO `qiu_role_menu` VALUES (79, 103);
INSERT INTO `qiu_role_menu` VALUES (79, 104);
INSERT INTO `qiu_role_menu` VALUES (79, 105);
INSERT INTO `qiu_role_menu` VALUES (79, 106);
INSERT INTO `qiu_role_menu` VALUES (79, 107);
INSERT INTO `qiu_role_menu` VALUES (79, 108);
INSERT INTO `qiu_role_menu` VALUES (79, 173);
INSERT INTO `qiu_role_menu` VALUES (79, 109);
INSERT INTO `qiu_role_menu` VALUES (79, 110);
INSERT INTO `qiu_role_menu` VALUES (79, 174);
INSERT INTO `qiu_role_menu` VALUES (80, 137);
INSERT INTO `qiu_role_menu` VALUES (80, 138);
INSERT INTO `qiu_role_menu` VALUES (80, 165);
INSERT INTO `qiu_role_menu` VALUES (80, 139);
INSERT INTO `qiu_role_menu` VALUES (80, 166);
INSERT INTO `qiu_role_menu` VALUES (1, 1);
INSERT INTO `qiu_role_menu` VALUES (1, 3);
INSERT INTO `qiu_role_menu` VALUES (1, 11);
INSERT INTO `qiu_role_menu` VALUES (1, 12);
INSERT INTO `qiu_role_menu` VALUES (1, 13);
INSERT INTO `qiu_role_menu` VALUES (1, 160);
INSERT INTO `qiu_role_menu` VALUES (1, 161);
INSERT INTO `qiu_role_menu` VALUES (1, 4);
INSERT INTO `qiu_role_menu` VALUES (1, 14);
INSERT INTO `qiu_role_menu` VALUES (1, 15);
INSERT INTO `qiu_role_menu` VALUES (1, 16);
INSERT INTO `qiu_role_menu` VALUES (1, 162);
INSERT INTO `qiu_role_menu` VALUES (1, 5);
INSERT INTO `qiu_role_menu` VALUES (1, 17);
INSERT INTO `qiu_role_menu` VALUES (1, 18);
INSERT INTO `qiu_role_menu` VALUES (1, 19);
INSERT INTO `qiu_role_menu` VALUES (1, 163);
INSERT INTO `qiu_role_menu` VALUES (1, 6);
INSERT INTO `qiu_role_menu` VALUES (1, 20);
INSERT INTO `qiu_role_menu` VALUES (1, 21);
INSERT INTO `qiu_role_menu` VALUES (1, 22);
INSERT INTO `qiu_role_menu` VALUES (1, 164);
INSERT INTO `qiu_role_menu` VALUES (1, 2);
INSERT INTO `qiu_role_menu` VALUES (1, 8);
INSERT INTO `qiu_role_menu` VALUES (1, 23);
INSERT INTO `qiu_role_menu` VALUES (1, 10);
INSERT INTO `qiu_role_menu` VALUES (1, 24);
INSERT INTO `qiu_role_menu` VALUES (1, 170);
INSERT INTO `qiu_role_menu` VALUES (1, 136);
INSERT INTO `qiu_role_menu` VALUES (1, 171);
INSERT INTO `qiu_role_menu` VALUES (1, 172);
INSERT INTO `qiu_role_menu` VALUES (1, 127);
INSERT INTO `qiu_role_menu` VALUES (1, 128);
INSERT INTO `qiu_role_menu` VALUES (1, 129);
INSERT INTO `qiu_role_menu` VALUES (1, 131);
INSERT INTO `qiu_role_menu` VALUES (1, 175);
INSERT INTO `qiu_role_menu` VALUES (1, 101);
INSERT INTO `qiu_role_menu` VALUES (1, 102);
INSERT INTO `qiu_role_menu` VALUES (1, 103);
INSERT INTO `qiu_role_menu` VALUES (1, 104);
INSERT INTO `qiu_role_menu` VALUES (1, 105);
INSERT INTO `qiu_role_menu` VALUES (1, 106);
INSERT INTO `qiu_role_menu` VALUES (1, 107);
INSERT INTO `qiu_role_menu` VALUES (1, 108);
INSERT INTO `qiu_role_menu` VALUES (1, 173);
INSERT INTO `qiu_role_menu` VALUES (1, 109);
INSERT INTO `qiu_role_menu` VALUES (1, 110);
INSERT INTO `qiu_role_menu` VALUES (1, 174);
INSERT INTO `qiu_role_menu` VALUES (1, 137);
INSERT INTO `qiu_role_menu` VALUES (1, 138);
INSERT INTO `qiu_role_menu` VALUES (1, 165);
INSERT INTO `qiu_role_menu` VALUES (1, 139);
INSERT INTO `qiu_role_menu` VALUES (1, 166);
INSERT INTO `qiu_role_menu` VALUES (1, 115);
INSERT INTO `qiu_role_menu` VALUES (1, 132);
INSERT INTO `qiu_role_menu` VALUES (1, 133);
INSERT INTO `qiu_role_menu` VALUES (1, 135);
INSERT INTO `qiu_role_menu` VALUES (1, 134);
INSERT INTO `qiu_role_menu` VALUES (1, 126);
INSERT INTO `qiu_role_menu` VALUES (1, 159);
INSERT INTO `qiu_role_menu` VALUES (1, 116);
INSERT INTO `qiu_role_menu` VALUES (1, 117);
INSERT INTO `qiu_role_menu` VALUES (1, 119);
INSERT INTO `qiu_role_menu` VALUES (1, 120);
INSERT INTO `qiu_role_menu` VALUES (1, 121);
INSERT INTO `qiu_role_menu` VALUES (1, 122);
INSERT INTO `qiu_role_menu` VALUES (1, 123);
INSERT INTO `qiu_role_menu` VALUES (1, 118);
INSERT INTO `qiu_role_menu` VALUES (1, 125);
INSERT INTO `qiu_role_menu` VALUES (1, 167);
INSERT INTO `qiu_role_menu` VALUES (1, 168);
INSERT INTO `qiu_role_menu` VALUES (1, 169);
INSERT INTO `qiu_role_menu` VALUES (1, 178);
INSERT INTO `qiu_role_menu` VALUES (2, 1);
INSERT INTO `qiu_role_menu` VALUES (2, 3);
INSERT INTO `qiu_role_menu` VALUES (2, 161);
INSERT INTO `qiu_role_menu` VALUES (2, 4);
INSERT INTO `qiu_role_menu` VALUES (2, 14);
INSERT INTO `qiu_role_menu` VALUES (2, 162);
INSERT INTO `qiu_role_menu` VALUES (2, 5);
INSERT INTO `qiu_role_menu` VALUES (2, 17);
INSERT INTO `qiu_role_menu` VALUES (2, 163);
INSERT INTO `qiu_role_menu` VALUES (2, 6);
INSERT INTO `qiu_role_menu` VALUES (2, 20);
INSERT INTO `qiu_role_menu` VALUES (2, 164);
INSERT INTO `qiu_role_menu` VALUES (2, 2);
INSERT INTO `qiu_role_menu` VALUES (2, 8);
INSERT INTO `qiu_role_menu` VALUES (2, 10);
INSERT INTO `qiu_role_menu` VALUES (2, 170);
INSERT INTO `qiu_role_menu` VALUES (2, 136);
INSERT INTO `qiu_role_menu` VALUES (2, 172);
INSERT INTO `qiu_role_menu` VALUES (2, 127);
INSERT INTO `qiu_role_menu` VALUES (2, 128);
INSERT INTO `qiu_role_menu` VALUES (2, 129);
INSERT INTO `qiu_role_menu` VALUES (2, 131);
INSERT INTO `qiu_role_menu` VALUES (2, 175);
INSERT INTO `qiu_role_menu` VALUES (2, 101);
INSERT INTO `qiu_role_menu` VALUES (2, 102);
INSERT INTO `qiu_role_menu` VALUES (2, 173);
INSERT INTO `qiu_role_menu` VALUES (2, 109);
INSERT INTO `qiu_role_menu` VALUES (2, 174);
INSERT INTO `qiu_role_menu` VALUES (2, 137);
INSERT INTO `qiu_role_menu` VALUES (2, 138);
INSERT INTO `qiu_role_menu` VALUES (2, 139);
INSERT INTO `qiu_role_menu` VALUES (2, 115);
INSERT INTO `qiu_role_menu` VALUES (2, 132);
INSERT INTO `qiu_role_menu` VALUES (2, 133);
INSERT INTO `qiu_role_menu` VALUES (2, 135);
INSERT INTO `qiu_role_menu` VALUES (2, 134);
INSERT INTO `qiu_role_menu` VALUES (2, 126);
INSERT INTO `qiu_role_menu` VALUES (2, 159);
INSERT INTO `qiu_role_menu` VALUES (2, 116);
INSERT INTO `qiu_role_menu` VALUES (2, 117);
INSERT INTO `qiu_role_menu` VALUES (2, 119);
INSERT INTO `qiu_role_menu` VALUES (2, 120);
INSERT INTO `qiu_role_menu` VALUES (2, 121);
INSERT INTO `qiu_role_menu` VALUES (2, 122);
INSERT INTO `qiu_role_menu` VALUES (2, 123);
INSERT INTO `qiu_role_menu` VALUES (2, 118);
INSERT INTO `qiu_role_menu` VALUES (2, 125);
INSERT INTO `qiu_role_menu` VALUES (2, 167);
INSERT INTO `qiu_role_menu` VALUES (2, 168);
INSERT INTO `qiu_role_menu` VALUES (2, 169);
INSERT INTO `qiu_role_menu` VALUES (2, 178);

-- ----------------------------
-- Table structure for qiu_user_role
-- ----------------------------
DROP TABLE IF EXISTS `qiu_user_role`;
CREATE TABLE `qiu_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  INDEX `qiu_user_role_user_id`(`user_id`) USING BTREE,
  INDEX `qiu_user_role_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qiu_user_role
-- ----------------------------
INSERT INTO `qiu_user_role` VALUES (1, 1);
INSERT INTO `qiu_user_role` VALUES (2, 2);
INSERT INTO `qiu_user_role` VALUES (3, 77);
INSERT INTO `qiu_user_role` VALUES (4, 78);
INSERT INTO `qiu_user_role` VALUES (5, 79);
INSERT INTO `qiu_user_role` VALUES (6, 80);
INSERT INTO `qiu_user_role` VALUES (7, 78);
INSERT INTO `qiu_user_role` VALUES (7, 79);
INSERT INTO `qiu_user_role` VALUES (7, 80);

SET FOREIGN_KEY_CHECKS = 1;
