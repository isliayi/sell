package com.moon.sell.aspect;

import com.moon.sell.constant.CookieConstant;
import com.moon.sell.constant.RedisConstant;
import com.moon.sell.exception.SellerAuthorizeException;
import com.moon.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.ServletContextAttributeExporter;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author moonglade on 2019-01-27.
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public SellerAuthorizeAspect(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


}
