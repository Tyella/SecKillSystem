package com.tyella.seckill.dao;

import com.tyella.seckill.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordDao {

    String TABLE_NAME = "record";
    String INSERT_FIELDS = " product,user,createTime,state,stateInfo ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values(#{product},#{user},#{createTime},#{state},#{stateInfo})"})
    boolean insertRecord(Record record);


}
