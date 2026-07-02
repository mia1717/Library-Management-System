/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : db_book1

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 19/06/2025 08:09:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `author` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Press` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `priceIn` float NOT NULL,
  `priceOut` float NOT NULL,
  `stockNumber` int NOT NULL,
  `discount` int NULL DEFAULT NULL,
  `bookDesc` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `bookTypeId` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bookTypeId`(`bookDesc`(255) ASC) USING BTREE,
  INDEX `bookTypeId_2`(`bookTypeId` ASC) USING BTREE,
  INDEX `bookName`(`bookName` ASC) USING BTREE,
  CONSTRAINT `t_book_ibfk_1` FOREIGN KEY (`bookTypeId`) REFERENCES `t_booktype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (1, '平凡的世界', '路遥', '人民文学出版社', 20, 30, 93, 10, '一部描绘中国城乡社会生活的长篇小说', 1);
INSERT INTO `t_book` VALUES (2, '苏东坡传', '林语堂', '湖南文艺出版社', 30, 45, 47, 10, '关于苏东坡的传记', 2);
INSERT INTO `t_book` VALUES (3, '明朝那些事儿', '当年明月', '中国海关出版社', 25, 35, 79, 10, '以幽默风趣的方式讲述明朝历史', 3);
INSERT INTO `t_book` VALUES (4, '人工智能', '李开复', '浙江科学技术出版社', 40, 50, 58, 10, '关于人工智能的科普书籍', 4);
INSERT INTO `t_book` VALUES (5, '艺术的故事', '贡布里希', '广西美术出版社', 50, 60, 68, 10, '艺术史经典著作', 5);

-- ----------------------------
-- Table structure for t_booktype
-- ----------------------------
DROP TABLE IF EXISTS `t_booktype`;
CREATE TABLE `t_booktype`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookTypeName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `bookTypeDesc` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bookTypeName`(`bookTypeName` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_booktype
-- ----------------------------
INSERT INTO `t_booktype` VALUES (1, '小说', '虚构类文学作品');
INSERT INTO `t_booktype` VALUES (2, '传记', '人物生平事迹记录');
INSERT INTO `t_booktype` VALUES (3, '历史', '历史事件与人物研究');
INSERT INTO `t_booktype` VALUES (4, '科技', '科学技术相关书籍');
INSERT INTO `t_booktype` VALUES (5, '艺术', '艺术类书籍，如绘画、音乐等');

-- ----------------------------
-- Table structure for t_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `managerName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_manager
-- ----------------------------
INSERT INTO `t_manager` VALUES (2, '1', '1');

-- ----------------------------
-- Table structure for t_sale_order
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_order`;
CREATE TABLE `t_sale_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '订单编号',
  `total_amount` float NOT NULL COMMENT '总金额',
  `total_profit` float NOT NULL COMMENT '总利润',
  `sale_date` date NOT NULL COMMENT '销售日期',
  `discount_type` int NOT NULL COMMENT '折扣类型(1原价/2折扣价)',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_no`(`order_no` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sale_order
-- ----------------------------
INSERT INTO `t_sale_order` VALUES (1, 'SO20250619001', 100, 30, '2025-06-19', 1, '订单1');
INSERT INTO `t_sale_order` VALUES (2, 'SO20250619002', 200, 60, '2025-06-19', 2, '订单2');
INSERT INTO `t_sale_order` VALUES (3, 'SO20250620001', 150, 45, '2025-06-20', 1, '订单3');
INSERT INTO `t_sale_order` VALUES (6, 'SO20250619074826', 30, 10, '2025-06-19', 1, '销售订单');
INSERT INTO `t_sale_order` VALUES (7, 'SO20250619074850', 30, 10, '2025-06-19', 1, '销售订单');
INSERT INTO `t_sale_order` VALUES (8, 'SO20250619074917', 60, 20, '2025-06-19', 1, '销售订单');
INSERT INTO `t_sale_order` VALUES (9, 'SO20250619075816', 120, 40, '2025-06-19', 1, '批量销售');
INSERT INTO `t_sale_order` VALUES (10, 'SO20250619080001', 145, 35, '2025-06-19', 1, '批量销售');
INSERT INTO `t_sale_order` VALUES (11, 'SO20250619080100', 155, 30, '2025-06-19', 1, '批量销售');

-- ----------------------------
-- Table structure for t_trade
-- ----------------------------
DROP TABLE IF EXISTS `t_trade`;
CREATE TABLE `t_trade`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `bookId` int NOT NULL,
  `discount` int NOT NULL DEFAULT 10,
  `priceIn` float NOT NULL,
  `realSalePrice` float NOT NULL,
  `saleNumber` int NOT NULL,
  `profit` float NOT NULL,
  `saleDate` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bookId`(`bookId` ASC) USING BTREE,
  INDEX `order_id`(`order_id` ASC) USING BTREE,
  CONSTRAINT `t_trade_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `t_book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_trade_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `t_sale_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 312 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_trade
-- ----------------------------
INSERT INTO `t_trade` VALUES (1, 1, 1, 10, 20, 30, 2, 20, '2025-06-19');
INSERT INTO `t_trade` VALUES (2, 1, 2, 10, 30, 45, 1, 15, '2025-06-19');
INSERT INTO `t_trade` VALUES (3, 2, 3, 10, 25, 35, 3, 30, '2025-06-19');
INSERT INTO `t_trade` VALUES (4, 3, 4, 10, 40, 50, 1, 10, '2025-06-20');
INSERT INTO `t_trade` VALUES (312, 6, 1, 10, 20, 30, 1, 10, '2025-06-19');
INSERT INTO `t_trade` VALUES (313, 7, 1, 10, 20, 30, 1, 10, '2025-06-19');
INSERT INTO `t_trade` VALUES (314, 8, 1, 10, 20, 30, 2, 20, '2025-06-19');
INSERT INTO `t_trade` VALUES (315, 9, 1, 10, 20, 30, 1, 10, '2025-06-19');
INSERT INTO `t_trade` VALUES (316, 9, 2, 10, 30, 45, 2, 30, '2025-06-19');
INSERT INTO `t_trade` VALUES (317, 10, 2, 10, 30, 45, 1, 15, '2025-06-19');
INSERT INTO `t_trade` VALUES (318, 10, 4, 10, 40, 50, 2, 20, '2025-06-19');
INSERT INTO `t_trade` VALUES (319, 11, 3, 10, 25, 35, 1, 10, '2025-06-19');
INSERT INTO `t_trade` VALUES (320, 11, 5, 10, 50, 60, 2, 20, '2025-06-19');

-- ----------------------------
-- View structure for v_booktype_stock_count
-- ----------------------------
DROP VIEW IF EXISTS `v_booktype_stock_count`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_booktype_stock_count` AS select `t_book`.`bookTypeId` AS `bookTypeId`,`t_booktype`.`bookTypeName` AS `bookTypeName`,count(0) AS `totalStock` from (`t_book` join `t_booktype` on((`t_book`.`bookTypeId` = `t_booktype`.`id`))) group by `t_book`.`bookTypeId`;

-- ----------------------------
-- Procedure structure for GetBookTradeInfo
-- ----------------------------
DROP PROCEDURE IF EXISTS `GetBookTradeInfo`;
delimiter ;;
CREATE PROCEDURE `GetBookTradeInfo`(IN startDate DATE, IN endDate DATE)
BEGIN
    -- 定义游标用于遍历书籍类型ID
    DECLARE done INT DEFAULT FALSE;
    DECLARE curBookTypeId INT;
    DECLARE cur CURSOR FOR 
        SELECT DISTINCT bookTypeId FROM t_book WHERE bookTypeId IS NOT NULL;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- 创建一个临时表来存储查询结果
    CREATE TEMPORARY TABLE IF NOT EXISTS temp_book_trade_info (
        bookTypeName VARCHAR(20),
        totalBooks INT,
        totalPriceIn FLOAT,
        totalSaleRevenue FLOAT,
        totalProfit FLOAT
    );

    -- 打开游标
    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO curBookTypeId;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- 对于每个书籍类型，查询该类型在指定时间段内的进货和销售情况
        INSERT INTO temp_book_trade_info
        SELECT 
            bt.bookTypeName,
            COUNT(DISTINCT t.id) AS totalBooks,
            SUM(t.priceIn) AS totalPriceIn,
            SUM(tr.realSalePrice * tr.saleNumber) AS totalSaleRevenue,
            SUM(tr.profit * tr.saleNumber) AS totalProfit
        FROM t_book t
        JOIN t_booktype bt ON t.bookTypeId = bt.id
        LEFT JOIN t_trade tr ON t.id = tr.bookId
        WHERE t.bookTypeId = curBookTypeId AND tr.saleDate BETWEEN startDate AND endDate
        GROUP BY bt.bookTypeName;
    END LOOP;

    -- 关闭游标
    CLOSE cur;

    -- 从临时表中查询所有结果
    SELECT * FROM temp_book_trade_info;

    -- 删除临时表
    DROP TEMPORARY TABLE IF EXISTS temp_book_trade_info;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table t_book
-- ----------------------------
DROP TRIGGER IF EXISTS `before_insert_check_stock`;
delimiter ;;
CREATE TRIGGER `before_insert_check_stock` BEFORE INSERT ON `t_book` FOR EACH ROW BEGIN
    -- 如果库存数量未指定或为负数，则设置为 0
    IF NEW.stockNumber IS NULL OR NEW.stockNumber < 0 THEN
        SET NEW.stockNumber = 0;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table t_book
-- ----------------------------
DROP TRIGGER IF EXISTS `before_update_check_discount`;
delimiter ;;
CREATE TRIGGER `before_update_check_discount` BEFORE UPDATE ON `t_book` FOR EACH ROW BEGIN
    -- 计算折扣后的售价
    DECLARE discountedPrice FLOAT;
    SET discountedPrice = NEW.priceOut * (NEW.discount / 10);

    -- 如果折扣后的售价低于进价，则取消更新并提示错误
    IF discountedPrice < NEW.priceIn THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = '折扣后的售价不能低于进价！';
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table t_trade
-- ----------------------------
DROP TRIGGER IF EXISTS `after_insert_update_stock`;
delimiter ;;
CREATE TRIGGER `after_insert_update_stock` AFTER INSERT ON `t_trade` FOR EACH ROW BEGIN
    -- 当 t_trade 表插入新记录时，增加相应图书的库存量
    UPDATE t_book
    SET stockNumber = stockNumber + NEW.saleNumber - NEW.saleNumber
    WHERE id = NEW.bookId;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
