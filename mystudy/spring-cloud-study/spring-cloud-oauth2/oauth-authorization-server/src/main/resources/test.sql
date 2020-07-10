SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author`  (
    `author_id` int(11) NOT NULL,
    `author_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`author_id`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES (1, 'wuzz');
INSERT INTO `author` VALUES (2, 'mic');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
    `bid` int(11) NOT NULL,
    `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `author_id` int(11) NULL DEFAULT NULL,
    PRIMARY KEY (`bid`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, '1111', 1);
INSERT INTO `blog` VALUES (2, '2222', 1);
INSERT INTO `blog` VALUES (3, '3333', 2);
INSERT INTO `blog` VALUES (4, '4444', 2);
INSERT INTO `blog` VALUES (5, '5555', 2);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
    `comment_id` int(11) NOT NULL,
    `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `bid` int(11) NULL DEFAULT NULL,
    PRIMARY KEY (`comment_id`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '1', 1);
INSERT INTO `comment` VALUES (2, '2', 1);

SET FOREIGN_KEY_CHECKS = 1;
