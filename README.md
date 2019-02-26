# 秒杀系统

利用springboot搭建一个秒杀系统，使用框架springboot+redis+rabbitmq+mysql

## 具体内容
    对高并发高负载情形下的应用场景进行分析，以高效地处理资源竞争为目的，设计了一个秒杀系统。

    使用Redis作为原子计数器（watch事务+decr操作）,RabbitMQ作为消息队列记录用户抢购行为，MySQL做异步存储
    
    使用JMeter进行压力与性能测试（实验设计的是10秒内产生3000个请求）

