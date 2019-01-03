package com.moon.sell.enums;

import lombok.Getter;

/**
 * 返回结果异常枚举
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),

    ORDER_NOT_EXIST(12,"商品订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13,"商品订单详情不存在"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
