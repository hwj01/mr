package com.hwj.server.controller;

import com.hwj.server.config.ConfigGril;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class GrilController {

    @Resource
    private ConfigGril gril;

    @GetMapping("/gril/print")
    public String print(){
        return gril.getAge()+"";
    }
}
