package com.panda.demo.transfer.entity;


import java.io.Serializable;

/**
 * @author zhaozhengkang
 * @description
 * @date 2021/2/24 16:50
 */
public class TransferDTO implements Serializable {
    private int fromUid;
    private int toUid;
    private int amount;

    public int getFromUid() {
        return fromUid;
    }

    public void setFromUid(int fromUid) {
        this.fromUid = fromUid;
    }

    public int getToUid() {
        return toUid;
    }

    public void setToUid(int toUid) {
        this.toUid = toUid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
