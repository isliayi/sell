package com.moon.sell.service;

import com.moon.sell.dataobject.ProductInfo;
import com.moon.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author moonglade on 2018-12-25.
 * @version 1.0
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架的商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);


}
