package com.moon.sell.enums;

import lombok.Getter;

/**
 * 返回结果异常枚举
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),

    ORDER_MASTER_NOT_EXIST(12,"商品订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13,"商品订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_FAIL(15,"订单修改失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    ORDER_CANCEL_SUCCESS(20, "订单取消成功"),

    ORDER_FINISH_SUCCESS(21, "完结订单成功"),

    PRODUCT_STATUS_ERROR(22,"商品状态不正确"),

    LOGIN_FAIL(23,"登录失败，登录信息不正确"),

    LOGOUT_SUCCESS(24,"登出成功")

    ;



    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
