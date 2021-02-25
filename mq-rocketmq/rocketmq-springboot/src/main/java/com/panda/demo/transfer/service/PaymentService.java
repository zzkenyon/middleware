package com.panda.demo.transfer.service;

import com.alibaba.fastjson.JSON;
import com.panda.demo.transfer.entity.TransferDTO;
import com.panda.demo.transfer.mapper.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author zhaozhengkang
 * @description
 * @date 2021/2/25 09:18
 */
@Service
@Slf4j
public class PaymentService {
    @Autowired
    private PaymentMapper mapper;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public void sendTransactionMsg(TransferDTO transferDTO) {
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(transferDTO)).build();
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction(
                "txProducer_group",
                "transfer_test",
                message,null);
        log.info("发送事务消息");
    }

    public int insertIntoPayment(TransferDTO transferDTO){
        return mapper.insertIntoPayment(transferDTO);
    }

}
