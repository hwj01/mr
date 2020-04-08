package com.client.client.result;

import lombok.Data;

@Data
public class ResultVo<T> {
    /**
     * 状态吗
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 内容
     */
    private T data;

    public static ResultVo success(Object data){
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(200);
        resultVo.setMsg("成功");
        resultVo.setData(data);
        return resultVo;
    }
}
