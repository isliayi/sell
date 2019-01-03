package com.moon.sell.utils;

import java.util.Random;

/**
 * @author moonglade on 2019-01-02.
 * @version 1.0
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间加随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);

    }
}
