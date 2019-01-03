package com.moon.sell.repository;

import com.moon.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author moonglade on 2019-01-01.
 * @version 1.0
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String > {
    List<OrderDetail> findByOrderId(String orderId);
}
