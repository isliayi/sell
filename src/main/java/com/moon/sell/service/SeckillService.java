package com.moon.sell.service;

/**
 * @author moonglade on 2019-02-02.
 * @version 1.0
 */
public interface SeckillService {
    String queryMap(String productId);

    String querySeckillProductInfo(String productId);

    void orderProductMockDiffUser(String productId);


}
