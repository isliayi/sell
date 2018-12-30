package com.moon.sell.service;

import com.moon.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author moonglade on 2018-12-23.
 * @version 1.0
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
