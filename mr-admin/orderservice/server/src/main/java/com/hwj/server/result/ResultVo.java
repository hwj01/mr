package com.hwj.server.result;

import lombok.Data;

@Data
public class ResultVo<T> {
    private Integer code;
    private String msg;
    private T data;

    public static ResultVo success(Object data) {
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(200);
        resultVo.setMsg("成功");
        resultVo.setData(data);
        return resultVo;
    }
}
