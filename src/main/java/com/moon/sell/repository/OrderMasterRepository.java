package com.moon.sell.repository;

import com.moon.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author moonglade on 2019-01-01.
 * @version 1.0
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String > {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);

}
