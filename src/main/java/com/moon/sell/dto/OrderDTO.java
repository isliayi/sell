package com.moon.sell.dto;

import com.moon.sell.dataobject.OrderDetail;
import com.moon.sell.enums.OrderStatusEnum;
import com.moon.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author moonglade on 2019-01-02.
 * @version 1.0
 */
@Data
public class OrderDTO {

    //    订单id
    private String orderId;

    //    买家名字
    private String buyerName;

    //    买家电话
    private String buyerPhone;

    //    买家地址
    private String buyerAddress;

    //    买家微信Openid
    private String buyerOpenid;

    //    订单总金额
    private BigDecimal orderAmount;

    //    订单状态，默认为新下单
    private Integer orderStatus;

    //    支付状态，默认为0未支付
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
