package com.panda.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.panda.entity.Merchant;
import com.panda.mapper.MerchantMapper;
import com.panda.service.MerchantService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {


    @Value("${com.panda.topicexchange}")
    private String topicExchange;

    @Value("${com.panda.topicroutingkey1}")
    private String topicRoutingKey;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    AmqpTemplate mqTemplate;


    @Override
    public List<Merchant> getMerchantList(String name, int page, int limit) {
        return  merchantMapper.getMerchantList(name,page,limit);
    }

    @Override
    public Merchant getMerchantById(Integer id) {
        return merchantMapper.getMerchantById(id);
    }

    @Override
    public int add(Merchant merchant) {
        int k = merchantMapper.add(merchant);
        System.out.println("aaa : "+merchant.getId());
        JSONObject title = new JSONObject();
        String jsonBody = JSONObject.toJSONString(merchant);
        title.put("type","add");
        title.put("desc","新增商户");
        title.put("content",jsonBody);
        mqTemplate.convertAndSend(topicExchange,topicRoutingKey, title.toJSONString());

        return k;
    }

    @Override
    public int updateState(Merchant merchant) {

        int k = merchantMapper.updateState(merchant);

        JSONObject title = new JSONObject();
        String jsonBody = JSONObject.toJSONString(merchant);
        title.put("type","state");
        title.put("desc","更新商户状态");
        title.put("content",jsonBody);
        mqTemplate.convertAndSend(topicExchange,topicRoutingKey, title.toJSONString());

        return k;
    }

    @Override
    public int update(Merchant merchant) {

        int k = merchantMapper.update(merchant);

        // 发送消息失败了？？？
        JSONObject title = new JSONObject();
        String jsonBody = JSONObject.toJSONString(merchant);
        title.put("type","update");
        title.put("desc","更新商户信息");
        title.put("content",jsonBody);
        mqTemplate.convertAndSend(topicExchange,topicRoutingKey, title.toJSONString());

        return k;
    }

    @Override
    public int delete(Integer id) {

        int k = merchantMapper.delete(id);

        JSONObject title = new JSONObject();
        Merchant merchant = new Merchant();
        merchant.setId(id);
        String jsonBody = JSONObject.toJSONString(merchant);
        title.put("type","delete");
        title.put("desc","删除商户");
        title.put("content",jsonBody);

        mqTemplate.convertAndSend(topicExchange,topicRoutingKey, title.toJSONString());

        return k;
    }

    @Override
    public int getMerchantCount() {

        return merchantMapper.getMerchantCount();
    }
}
