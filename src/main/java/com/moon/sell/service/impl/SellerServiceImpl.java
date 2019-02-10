package com.moon.sell.service.impl;

import com.moon.sell.dataobject.SellerInfo;
import com.moon.sell.repository.SellerInfoRepository;
import com.moon.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moonglade on 2019-01-25.
 * @version 1.0
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
