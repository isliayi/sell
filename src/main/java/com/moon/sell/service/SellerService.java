package com.moon.sell.service;

import com.moon.sell.dataobject.SellerInfo;
import org.springframework.stereotype.Service;

/**
 * @author moonglade on 2019-01-25.
 * @version 1.0
 */
public interface SellerService {

    /**
     * 通过openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
