package com.panda.demo.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.panda.demo.transfer.entity.TransferDTO;
import com.panda.demo.transfer.service.PaymentService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.nio.charset.StandardCharsets;

/**
 * @author zhaozhengkang
 * @description
 * @date 2021/2/25 10:07
 */
@Component
@RocketMQTransactionListener(txProducerGroup = "txProducer_group")
public class TransactionProducerListener implements RocketMQLocalTransactionListener {
    @Autowired
    private PaymentService service;
    /**
     * 生产者执行本地事务
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String jsonString = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
        JSONObject json = JSON.parseObject(jsonString);
        TransferDTO transferDTO = JSON.toJavaObject(json,TransferDTO.class);
        TransactionStatus status = null;
        try {
            int result = service.insertIntoPayment(transferDTO);
            if (result < 1) {
                return RocketMQLocalTransactionState.ROLLBACK;
            }
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            if (status != null) {
                return RocketMQLocalTransactionState.ROLLBACK;
            }
        }
        return null;
    }

    /**
     * 查询生产者本地事务状态
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        Object o = msg.getHeaders().get("msg");
        if (o == null) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        TransferDTO transferDTO = (TransferDTO)o;
        TransactionStatus status = null;
        return RocketMQLocalTransactionState.COMMIT;
    }
}
