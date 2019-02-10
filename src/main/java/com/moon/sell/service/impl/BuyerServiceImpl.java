package com.moon.sell.service.impl;

import com.moon.sell.dto.OrderDTO;
import com.moon.sell.enums.ResultEnum;
import com.moon.sell.exception.SellException;
import com.moon.sell.service.BuyerService;
import com.moon.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moonglade on 2019-01-09.
 * @version 1.0
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    private final OrderService orderService;

    @Autowired
    public BuyerServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public void cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO=checkOrderOwner(openid, orderId);
        if(orderDTO==null){
            log.error("[取消订单]查不到该订单,orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_MASTER_NOT_EXIST);
        }
        orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        //判断是否是自己的订单
        if(!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("[查询订单]订单的openid不一致,openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
