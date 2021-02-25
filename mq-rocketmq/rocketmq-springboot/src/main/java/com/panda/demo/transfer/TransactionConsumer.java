package com.panda.demo.transfer;

import com.alibaba.fastjson.JSON;
import com.panda.demo.transfer.entity.TransferDTO;
import com.panda.demo.transfer.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang
 * @description
 * @date 2021/2/25 09:59
 */
@Component
@RocketMQMessageListener(consumerGroup = "txConsumer_group",topic = "transfer_test")
@Slf4j
public class TransactionConsumer implements RocketMQListener<String> {

    @Autowired
    private AccountService service;

    @Override
    public void onMessage(String message) {
        log.info("下游开始消费消息");
        TransferDTO dto = JSON.toJavaObject(JSON.parseObject(message),TransferDTO.class);
        service.resolveTransfer(dto);
        log.info("下游处理完毕");
    }
}
