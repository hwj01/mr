package com.hwj.server.formVo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class OrderForm {
    @NotEmpty(message = "姓名不为空")
    private String name;
    @NotEmpty(message = "电话不为空")
    private String phone;
    @NotEmpty(message = "地址不为空")
    private String address;
    @NotEmpty(message = "opernid不为空")
    private String openid;
    @NotEmpty(message = "购物车不为空")
    private String items;
}
