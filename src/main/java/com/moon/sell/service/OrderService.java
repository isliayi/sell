package com.moon.sell.service;

import com.moon.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author moonglade on 2019-01-02.
 * @version 1.0
 */
public interface OrderService {



    /** 创建订单. **/
    OrderDTO create(OrderDTO orderDTO);

    /** 查询单个订单. **/
    OrderDTO findOne(String orderId);

    /** 查询订单列表. **/
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** 取消订单. **/
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结列表. **/
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付列表. **/
    OrderDTO paid(OrderDTO orderDTO);


}
