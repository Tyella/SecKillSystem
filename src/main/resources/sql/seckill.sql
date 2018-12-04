
USE seckill;

DROP TABLE IF EXISTS product;
CREATE TABLE product(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "商品id",
  productName VARCHAR (20) NOT NULL COMMENT "产品名称",
  number INT NOT NULL COMMENT "库存数量",
  start_time TIMESTAMP NOT NULL COMMENT "秒杀开启时间",
  end_time TIMESTAMP NOT NULL COMMENT "秒杀结束时间",
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "订单创建时间",
  /* 建立索引，方便后续按照时间查询 */
  KEY idx_start_time (start_time),
  KEY idx_end_time (end_time),
  KEY idx_create_time (create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT "秒杀商品表";


DROP TABLE IF EXISTS success_killed;
CREATE TABLE success_killed(
  id BIGINT NOT NULL COMMENT "秒杀商品id",
  user_phone BIGINT NOT NULL COMMENT "用户手机号",
  state TINYINT NOT NULL DEFAULT -1 COMMENT "秒杀状态标示：-1：无效 0：成功 1：" /* TODO */,
  create_time TIMESTAMP NOT NULL COMMENT "创建时间",
  PRIMARY KEY (id, user_phone),  /*联合主健,防止重复秒杀*/
    /* 建立索引，方便查询*/
  KEY idx_create_time (create_time)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT "秒杀成功明细表";

DROP TABLE IF EXISTS user;
CREATE TABLE user(
  ID BIGINT NOT NULL COMMENT "用户id",
  userName VARCHAR (20) NOT NULL COMMENT "用户名",
  password VARCHAR (20) NOT NULL COMMENT "用户密码",
  user_phone BIGINT NOT NULL COMMENT "用户手机号"
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT "用户表";