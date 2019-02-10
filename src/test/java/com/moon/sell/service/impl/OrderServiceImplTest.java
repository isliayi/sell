package com.moon.sell.service.impl;

import com.moon.sell.dataobject.OrderDetail;
import com.moon.sell.dataobject.OrderMaster;
import com.moon.sell.dto.OrderDTO;
import com.moon.sell.enums.OrderStatusEnum;
import com.moon.sell.enums.PayStatusEnum;
import com.moon.sell.repository.OrderMasterRepository;
import com.moon.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        log.info("查询单个订单result={}",orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        PageRequest request=PageRequest.of(0,2);
        Page<OrderDTO>  orderDTOPage=orderService.findList(buyerOpenid,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO=orderService.findOne(orderid);
        OrderDTO result=orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO=orderService.findOne("1546485927257356450");
        OrderDTO result=orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHWED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO=orderService.findOne("1546485927257356450");
        OrderDTO result=orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void List() {
        PageRequest request=PageRequest.of(0,2);
        Page<OrderDTO>  orderDTOPage=orderService.findList(request);
        Assert.assertTrue("查询所有订单",orderDTOPage.getTotalElements()>0);
    }
}