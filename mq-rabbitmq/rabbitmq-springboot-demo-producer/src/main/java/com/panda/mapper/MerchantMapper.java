package com.panda.mapper;

import com.panda.entity.Merchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantMapper {

   Merchant getMerchantById(Integer sid);

    public List<Merchant> getMerchantList(@Param("name") String name, @Param("page") int page, @Param("limit") int limit);

    public int add(Merchant merchant);

    public int update(Merchant merchant);

    public int updateState(Merchant merchant);

    public int delete(Integer sid);

    int getMerchantCount();
}