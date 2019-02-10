package com.moon.sell.controller;

import com.moon.sell.dto.OrderDTO;
import com.moon.sell.enums.ResultEnum;
import com.moon.sell.exception.SellException;
import com.moon.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moonglade on 2019-01-10.
 * @version 1.0
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    private final OrderService orderService;

    @Autowired
    public SellerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer size, Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);

    }

    /**
     * 取消订单
     *
     * @param orderId 订单ID
     * @return
     */
    @GetMapping("cancel")
    public ModelAndView cancel(String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("[卖家端取消订单]发生异常{}", e);

            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }


        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");

    }

    /**
     * 订单详情
     *
     * @param orderId 订单ID
     * @return
     */
    @GetMapping("detail")
    public ModelAndView detail(String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO;
        try {
            orderDTO= orderService.findOne(orderId);
        }catch (SellException e) {
            log.error("[卖家端查询订单详情]发生异常{}", e);

            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    /**
     * 完结订单
     *
     * @param orderId 订单ID
     * @return
     */
    @GetMapping("finish")
    public ModelAndView finish(String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO= orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e) {
            log.error("[卖家端完结订单]发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

}
