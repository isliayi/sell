package com.moon.sell.utils;

import com.moon.sell.enums.CodeEnum;

/**
 * @author moonglade on 2019-01-16.
 * @version 1.0
 */
public class EnumUtil  {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
