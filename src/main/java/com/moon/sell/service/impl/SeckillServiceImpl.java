package com.moon.sell.service.impl;

import com.moon.sell.exception.SellException;
import com.moon.sell.service.SeckillService;
import com.moon.sell.utils.KeyUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moonglade on 2019-02-02.
 * @version 1.0
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    /**
     * 国庆活动，皮蛋粥特价，限量100000份
     */
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        //模拟多个表，商品信息表、库存表、秒杀成功订单表
        products=new HashMap<>();
        stock=new HashMap<>();
        orders=new HashMap<>();
        products.put("123456",100000);
        stock.put("123456",100000);
    }


    @Override
    public String queryMap(String productId) {
        return "国庆活动，皮蛋粥特价，限量份"
                +products.get(productId)
                +"还剩:"+stock.get(productId)+"份"
                +"该商品成功下单用户数目:"
                +orders.size()+"人";
    }

    @Override
    public String querySeckillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public synchronized void orderProductMockDiffUser(String productId) {
        //1.查询该商品库存,为0则活动结束
        int stockNum=stock.get(productId);
        if(stockNum==0){
            throw new SellException(100,"活动结束");
        }else {
            //2.下单(模拟不同用户的openid)
            orders.put(KeyUtil.genUniqueKey(),productId);
//            减库存
            stockNum=stockNum-1;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }
    }
}
