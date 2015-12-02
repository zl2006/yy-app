--测试数据 
INSERT INTO `TB_MONITOR_ENTITY` (`CFG_ID`, `NAME`, `MONITOR_TYPE`, `MONITOR_ENTITY_NODES`, `MONITOR_ENTITY_CFG`, `DESCRIPTION`, `STATUS`) VALUES ('1', '旁氏扫码应用', 'WEB_MONITOR', '[\"http://192.168.0.1:8088\",\"http://192.168.0.2:8088\"]', 'http://localhost:8080/monitor-console//resources/images/1.png', '只要是旁氏旗舰店的商品的全部都可以用,近期如果有什么护手霜、洗面奶、BB霜等等各类护肤用品的话,那就扫上一扫吧 ', '1');
INSERT INTO `TB_MONITOR_ENTITY` (`CFG_ID`, `NAME`, `MONITOR_TYPE`, `MONITOR_ENTITY_NODES`, `MONITOR_ENTITY_CFG`, `DESCRIPTION`, `STATUS`) VALUES ('2', '测试应用一', 'WEB_MONITOR', '[\"http://192.168.0.1:8088\",\"http://192.168.0.2:8088\"]', 'http://localhost:8080/monitor-console//resources/images/2.png', '我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的， ', '1');
INSERT INTO `TB_MONITOR_ENTITY` (`CFG_ID`, `NAME`, `MONITOR_TYPE`, `MONITOR_ENTITY_NODES`, `MONITOR_ENTITY_CFG`,  `DESCRIPTION`, `STATUS`) VALUES ('3', '测试应用二', 'WEB_MONITOR', '[\"http://192.168.0.1:8088\",\"http://192.168.0.2:8088\"]', 'http://localhost:8080/monitor-console//resources/images/3.png', '我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，我去你的，', '1');

UPDATE `TB_MONITOR_ENTITY` SET `MONITOR_ENTITY_CFG`='{\"icon\":\"http://localhost:8080/resources/images/1.png\"}' WHERE `CFG_ID`='1';
UPDATE `TB_MONITOR_ENTITY` SET `MONITOR_ENTITY_CFG`='{\"icon\":\"http://localhost:8080/resources/images/2.png\"}' WHERE `CFG_ID`='2';
UPDATE `TB_MONITOR_ENTITY` SET `MONITOR_ENTITY_CFG`='{\"icon\":\"http://localhost:8080/resources/images/3.png\"}' WHERE `CFG_ID`='3';


INSERT INTO `TB_MONITOR_ITEM` (`ITEM_ID`, `ENTITY_CFG_ID`, `NAME`, `ITEM_CFG`, `REQ_TIMES`, `SUCCESS_TIMES`, `FAILURE_TIMES`, `STATUS`) VALUES ('1', '1', '扫码验真', '{\"url\":\"/scan\",\"params\":\"code=De34F\"}', '100', '100', '0', '1');
INSERT INTO `TB_MONITOR_ITEM` (`ITEM_ID`, `ENTITY_CFG_ID`, `NAME`, `ITEM_CFG`, `REQ_TIMES`, `SUCCESS_TIMES`, `FAILURE_TIMES`, `STATUS`) VALUES ('2', '1', '会员中心', '{\"url\":\"/member\",\"params\":\"memberid=123\"}', '100', '99', '1', '1');
INSERT INTO `TB_MONITOR_ITEM` (`ITEM_ID`, `ENTITY_CFG_ID`, `NAME`, `ITEM_CFG`, `REQ_TIMES`, `SUCCESS_TIMES`, `FAILURE_TIMES`, `STATUS`) VALUES ('3', '1', '登录', '{\"url\":\"/login\",\"params\":\"username=abc&pwd=abc\"}', '100', '98', '2', '1');
INSERT INTO `TB_MONITOR_ITEM` (`ITEM_ID`, `ENTITY_CFG_ID`, `NAME`, `ITEM_CFG`, `REQ_TIMES`, `SUCCESS_TIMES`, `FAILURE_TIMES`, `STATUS`) VALUES ('4', '1', '发红包', '{\"url\":\"/sendhongbao\",\"params\":\"\"}', '100', '97', '3', '1');

UPDATE `TB_MONITOR_ENTITY` SET `MONITOR_ENTITY_CFG`='{\"icon\":\"http://localhost:8080/resources/images/1.png\",\"url\":\"http://www.panshi.com\"}' WHERE `CFG_ID`='1';
UPDATE `TB_MONITOR_ENTITY` SET `MONITOR_ENTITY_CFG`='{\"icon\":\"http://localhost:8080/resources/images/2.png\",\"url\":\"http://www.test1.com\"}' WHERE `CFG_ID`='2';
UPDATE `TB_MONITOR_ENTITY` SET `MONITOR_ENTITY_CFG`='{\"icon\":\"http://localhost:8080/resources/images/3.png\",\"url\":\"http://www.panshi.com\"}' WHERE `CFG_ID`='3';
