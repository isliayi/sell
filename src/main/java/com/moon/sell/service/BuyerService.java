package com.moon.sell.service;

import com.moon.sell.dto.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * @author moonglade on 2019-01-09.
 * @version 1.0
 */

public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    void cancelOrder(String openid, String orderId);
}
