package com.moon.sell.converter;

import com.moon.sell.dataobject.OrderMaster;
import com.moon.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author moonglade on 2019-01-06.
 * @version 1.0
 */
public class OrderMaster2OrderDTOConverter {
    private static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->
                convert(e)
                ).collect(Collectors.toList());
    }
}
