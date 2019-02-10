package com.moon.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author moonglade on 2019-01-26.
 * @version 1.0
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


    /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                           String name) {
        Map<String ,Cookie> cookieMap =readCookieMap(request);
        return cookieMap.getOrDefault(name, null);

    }


    /**
     * 讲cookie封装成map
     * @param request
     * @return
     */
    private static Map<String ,Cookie> readCookieMap(HttpServletRequest request){
        Map<String ,Cookie> cookieMap=new HashMap<>();
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;

    }
}
