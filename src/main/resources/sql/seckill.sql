
--数据库表设计

--秒杀商品表
CREATE TABLE product(
  id BIGINT NOT NULL AUT0_INCREMENT PRIMARY KEY COMMENT "商品id",
  productName VARCHAR (20) NOT NULL COMMENT "产品名称",
  number INT NOT NULL COMMENT "库存数量",
  start_time TIMESTAMP NOT NULL COMMENT "秒杀开启时间",
  end_time TIMESTAMP NOT NULL COMMENT "秒杀结束时间",
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "订单创建时间"
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF-8 COMMENT "秒杀商品表"


--秒杀成功明细表
CREATE TABLE success_killed(
  --秒杀商品id
  --用户手机号
  --秒杀状态标识：-1：无效，0：成功，1：已付款
  --创建时间
  ID BIGINT NOT NULL COMMENT "秒杀商品id",
  user_phone BIGINT NOT NULL COMMENT "用户手机号",
  state TINYINT NOT NULL DEFAULT -1 COMMENT "秒杀状态标示：-1：无效 0：成功 1：" /* TODO */
  create_time TIMESTAMP NOT NULL COMMENT "创建时间",
  PRIMARY KEY (seckill_id, user_phone)     --联合主健,防止重复秒杀
  key /* TODO */
)ENGING=InnoDB DEFAULT CHARSET=UTF-8 COMMENT "秒杀成功明细表"


--用户表
CREATE TABLE user(
  ID BIGINT NOT NULL COMMENT "用户id",
  userName VARCHAR (20) NOT NULL COMMENT "用户名",
  password VARCHAR (20) NOT NULL COMMENT "用户密码",
  user_phone BIGINT NOT NULL COMMENT "用户手机号"
)ENGING=InnoDB DEFAULT CHARSET=UTF-8 COOMMENT "用户表"