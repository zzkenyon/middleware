package com.panda.demo.transfer.mapper;

import com.panda.demo.transfer.entity.TransferDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhaozhengkang
 * @description
 * @date 2021/2/24 16:53
 */
@Mapper
public interface PaymentMapper {

    @Insert("INSERT INTO payment (from_uid,to_uid,amount) VALUES(" +
            "#{fromUid}," +
            "#{toUid}," +
            "#{amount})")
    int insertIntoPayment(TransferDTO transferDTO);


}
