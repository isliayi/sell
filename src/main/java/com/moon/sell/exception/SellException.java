package com.moon.sell.exception;

import com.moon.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @author moonglade on 2019-01-02.
 * @version 1.0
 */
@Getter
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code=resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
