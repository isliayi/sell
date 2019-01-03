package com.moon.sell.VO;

import lombok.Data;

/**
 * http返回的最外层对象
 * @author moonglade on 2018-12-30.
 * @version 1.0
 */
@Data
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;

}
