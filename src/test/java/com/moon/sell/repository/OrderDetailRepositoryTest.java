package com.moon.sell.repository;

import com.moon.sell.dataobject.OrderDetail;
import com.moon.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail() ;
        orderDetail.setDetailId ( "1234567810") ;
        orderDetail.setOrderId ( "111111112") ;
        orderDetail.setProductIcon( "http://xxxx.jpg");
        orderDetail.setProductId ("111l1112") ;
        orderDetail.setProductName ( "皮蛋粥") ;
        orderDetail.setProductPrice(new BigDecimal( 2.2));
        orderDetail.setProductQuantity(3) ;
        OrderDetail result=repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid(){
        List<OrderDetail>orderDetailList=repository.findByOrderId("11111111");
        Assert.assertNotEquals(0,orderDetailList.size());

    }

}