package com.moon.sell.service.impl;

import com.moon.sell.dataobject.OrderDetail;
import com.moon.sell.dataobject.OrderMaster;
import com.moon.sell.dto.OrderDTO;
import com.moon.sell.repository.OrderMasterRepository;
import com.moon.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String buyerOpenid="1100110";

    private final String orderid="1546502514838947432";

    @Test
    public void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("帅帅");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("11555544");
        orderDTO.setBuyerOpenid(buyerOpenid);

        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();

        OrderDetail o1= new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);

        OrderDetail o2= new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(10);
        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result=orderService.create(orderDTO);
        log.info("[创建订单]result={}",result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO=orderService.findOne(orderid);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}