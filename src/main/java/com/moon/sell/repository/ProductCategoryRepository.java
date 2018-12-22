package com.moon.sell.repository;

import com.moon.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author moonglade on 2018-12-20.
 * @version 1.0
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
