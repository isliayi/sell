package com.moon.sell.utils;

import com.moon.sell.VO.ResultVO;

/**
 * @author moonglade on 2018-12-31.
 * @version 1.0
 */
public class ResultVOUtil {

    /**
     * 接口请求成功
     * @param object data数据
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO<Object> resultVO=new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    /**
     * 接口请求失败
     * @param code 状态码
     * @param msg 错误信息
     * @return
     */
    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


}
