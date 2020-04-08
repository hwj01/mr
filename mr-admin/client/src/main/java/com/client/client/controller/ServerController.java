package com.client.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @GetMapping("msg")
    public String getMsg(){
        return "this is a service";
    }
}
