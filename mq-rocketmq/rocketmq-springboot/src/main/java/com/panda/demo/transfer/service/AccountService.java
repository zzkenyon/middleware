package com.panda.demo.transfer.service;

import com.panda.demo.transfer.entity.TransferDTO;
import com.panda.demo.transfer.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaozhengkang
 * @description
 * @date 2021/2/25 10:02
 */
@Service
@Slf4j
public class AccountService {
    @Autowired
    private AccountMapper mapper;
    @Transactional
    public void resolveTransfer(TransferDTO transferDTO) {
        int amount = transferDTO.getAmount();
        mapper.updateAccount(transferDTO.getFromUid(), -amount);
        mapper.updateAccount(transferDTO.getToUid(),amount);
    }
}
