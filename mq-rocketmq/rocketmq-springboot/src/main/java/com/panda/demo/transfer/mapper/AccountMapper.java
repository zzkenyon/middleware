package com.panda.demo.transfer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {
    @Update("UPDATE account SET amount=amount+#{a} WHERE id = #{uid}")
    int updateAccount(@Param("uid") int uid, @Param("a") int a);
}
