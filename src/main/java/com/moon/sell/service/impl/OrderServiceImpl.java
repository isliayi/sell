package com.moon.sell.service.impl;

import com.moon.sell.dataobject.OrderDetail;
import com.moon.sell.dataobject.OrderMaster;
import com.moon.sell.dataobject.ProductInfo;
import com.moon.sell.dto.CartDTO;
import com.moon.sell.dto.OrderDTO;
import com.moon.sell.enums.OrderStatusEnum;
import com.moon.sell.enums.ResultEnum;
import com.moon.sell.exception.SellException;
import com.moon.sell.repository.OrderDetailRepository;
import com.moon.sell.repository.OrderMasterRepository;
import com.moon.sell.service.OrderService;
import com.moon.sell.service.ProductService;
import com.moon.sell.utils.KeyUtil;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.rmi.transport.Connection;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author moonglade on 2019-01-02.
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;

    private final OrderDetailRepository orderDetailRepository;

    private final OrderMasterRepository orderMasterRepository;

    @Autowired
    public OrderServiceImpl(ProductService productService, OrderDetailRepository orderDetailRepository, OrderMasterRepository orderMasterRepository) {
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
        this.orderMasterRepository = orderMasterRepository;
    }

    //    创建订单
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId=KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);



//        1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
//        2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            //拷贝对象
            BeanUtils.copyProperties(productInfo,orderDetail);

            orderDetailRepository.save(orderDetail);
        }
//        3.写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(OrderStatusEnum.NEW.getCode());

        orderMasterRepository.save(orderMaster);

//        4.扣库存
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findById(orderId).get();
        if(orderMaster==null){
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        List<OrderDetail> orderDetailList=orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
