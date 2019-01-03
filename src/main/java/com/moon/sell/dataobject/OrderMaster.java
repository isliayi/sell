package com.moon.sell.dataobject;

import com.moon.sell.enums.OrderStatusEnum;
import com.moon.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 * @author moonglade on 2019-01-01.
 * @version 1.0
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

//    订单id
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

//    支付状态，默认为0未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;


}
