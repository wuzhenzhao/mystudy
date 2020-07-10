/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.101
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 192.168.1.101:3306
 Source Schema         : study

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 10/07/2020 17:20:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
    `user_id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
    `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
    `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `account_non_expired` tinyint(1) NULL DEFAULT NULL,
    `account_non_locked` tinyint(1) NULL DEFAULT NULL,
    `credentials_non_expired` tinyint(1) NULL DEFAULT NULL,
    `enabled` tinyint(1) NULL DEFAULT NULL,
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`user_id`) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'Jone', '18', 'test1@baomidou.com', 1, 1, 1, 1);
INSERT INTO `sys_user` VALUES (2, 'wuzz', '19', 'test1@baomidou.com', 1, 1, 1, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
    `role_id` int(255) NOT NULL AUTO_INCREMENT,
    `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`role_id`) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '技术总监', 'ROLE_JSZJ');
INSERT INTO `sys_role` VALUES (2, '架构师', 'ROLE_JGS');
INSERT INTO `sys_role` VALUES (3, '项目经理', 'ROLE_XMJL');
INSERT INTO `sys_role` VALUES (4, '技术主管', 'ROLE_JSZG');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
    `permission_id` int(255) NOT NULL AUTO_INCREMENT,
    `permission_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `permission_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`permission_id`) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '001', '所有权限');
INSERT INTO `sys_permission` VALUES (2, '002', '架构权限');
INSERT INTO `sys_permission` VALUES (3, '003', '项目权限');
INSERT INTO `sys_permission` VALUES (4, '004', 'code权限');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NULL DEFAULT NULL,
    `role_id` int(11) NULL DEFAULT NULL,
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1, 2);
INSERT INTO `sys_user_role` VALUES (3, 2, 1);
INSERT INTO `sys_user_role` VALUES (4, 2, 2);
INSERT INTO `sys_user_role` VALUES (5, 2, 3);
INSERT INTO `sys_user_role` VALUES (6, 2, 4);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `permission_id` int(11) NULL DEFAULT NULL,
    `role_id` int(11) NULL DEFAULT NULL,
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2, 2);
INSERT INTO `sys_role_permission` VALUES (3, 3, 3);
INSERT INTO `sys_role_permission` VALUES (4, 4, 4);
INSERT INTO `sys_role_permission` VALUES (5, 2, 1);

SET FOREIGN_KEY_CHECKS = 1;

