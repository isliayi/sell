package com.moon.sell.exception;

import com.moon.sell.enums.ResultEnum;

/**
 * @author moonglade on 2019-01-02.
 * @version 1.0
 */
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code=resultEnum.getCode();
    }
}
