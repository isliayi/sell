package com.moon.sell.controller;

import com.moon.sell.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moonglade on 2019-02-02.
 * @version 1.0
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {

    private final SeckillService seckillService;


    @Autowired
    public SecKillController(SeckillService seckillService) {
        this.seckillService = seckillService;
    }

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId){
        return seckillService.querySeckillProductInfo(productId);
    }

    /**
     * 秒杀，没有抢到获得"哎呦喂，xxxx"，抢到了会返回剩余的库存量
     * @param productId
     * @return
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId){
        log.info("@skill request,productId:"+productId);
        seckillService.orderProductMockDiffUser(productId);
        return seckillService.querySeckillProductInfo(productId);
    }
}
