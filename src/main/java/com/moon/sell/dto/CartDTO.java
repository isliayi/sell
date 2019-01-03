package com.moon.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @author moonglade on 2019-01-02.
 * @version 1.0
 */
@Data
public class CartDTO {

//    商品ID
    private String productId;

//    商品数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
