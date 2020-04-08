package com.hwj.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ClientController {

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("productMsg")
    public String getProductServer(){
        /**第一种方式：RestTemplate*/
        /*RestTemplate restTemplate = new RestTemplate();
        String template = restTemplate.getForObject("http://localhost:8703/msg", String.class);
        log.info("template{}",template);*/

        /**第二种方式：LoadBalancerClient*/
        ServiceInstance instance = loadBalancerClient.choose("client");
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://%s:%s/msg", instance.getHost(), instance.getPort());
        String forObject = restTemplate.getForObject(url, String.class);
        log.info("template{}",forObject);

        /**第二种方式：配置RestTemplate*/
        //String forObject = restTemplate.getForObject("http://client/msg",String.class);
        return forObject;
    }
}
