package com.client.client;

import com.client.client.controller.ProductCtroller;
import com.client.client.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest
class ClientApplicationTests {

    @Resource
    private ProductCtroller productCtroller;

    @Test
    void contextLoads() {
    }
    @Test
    void changeStock() {
        productCtroller.changeStock(Arrays.asList(new ProductDto("157875196366160022",2)));
    }
}
