CREATE TABLE `t_yi_ktick` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `symbol` varchar(128) DEFAULT NULL,
  `kline_id` bigint(20) DEFAULT NULL,
  `open` decimal(30,20) DEFAULT NULL,
  `close` decimal(30,20) DEFAULT NULL,
  `low` decimal(30,20) DEFAULT NULL,
  `high` decimal(30,20) DEFAULT NULL,
  `vol` decimal(30,20) DEFAULT NULL,
  `count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `kline_id_UNIQUE` (`kline_id`,`symbol`)
) ENGINE=InnoDB AUTO_INCREMENT=1872 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `t_yi_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `symbol` varchar(128) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `amount` decimal(30,20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `canceled_at` datetime DEFAULT NULL,
  `field_amount` decimal(30,20) DEFAULT NULL,
  `field_cash_amount` decimal(30,20) DEFAULT NULL,
  `field_fees` decimal(30,20) DEFAULT NULL,
  `finished_at` datetime DEFAULT NULL,
  `price` decimal(30,20) DEFAULT NULL,
  `source` varchar(128) DEFAULT NULL,
  `state` varchar(128) DEFAULT NULL,
  `type` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;