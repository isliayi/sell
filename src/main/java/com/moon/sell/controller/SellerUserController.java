package com.moon.sell.controller;

import com.moon.sell.constant.CookieConstant;
import com.moon.sell.constant.RedisConstant;
import com.moon.sell.dataobject.SellerInfo;
import com.moon.sell.enums.ResultEnum;
import com.moon.sell.service.SellerService;
import com.moon.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author moonglade on 2019-01-25.
 * @version 1.0
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    private final SellerService sellerService;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public SellerUserController(SellerService sellerService, StringRedisTemplate redisTemplate) {
        this.sellerService = sellerService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/login")
    public ModelAndView login(String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

//        openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }

//        设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);


//        设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);


        return new ModelAndView("redirect:/seller/order/list");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        //从cookie里查询
        Cookie cookie=CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie!=null){
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));


            //清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null ,0);

        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);

    }
}
