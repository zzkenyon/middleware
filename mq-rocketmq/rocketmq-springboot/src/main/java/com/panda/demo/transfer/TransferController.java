package com.panda.demo.transfer;

import com.panda.demo.transfer.entity.TransferDTO;
import com.panda.demo.transfer.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2021/2/24 16:45
 */
@RestController
public class TransferController {

    @Autowired
    private PaymentService service;

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferDTO transferDTO){
        service.sendTransactionMsg(transferDTO);
        return "success";
    }
}
