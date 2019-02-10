package com.moon.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.moon.sell.dataobject.OrderDetail;
import com.moon.sell.enums.OrderStatusEnum;
import com.moon.sell.enums.PayStatusEnum;
import com.moon.sell.utils.EnumUtil;
import com.moon.sell.utils.serializer.Data2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author moonglade on 2019-01-02.
 * @version 1.0
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonSerialize(using = Data2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Data2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
