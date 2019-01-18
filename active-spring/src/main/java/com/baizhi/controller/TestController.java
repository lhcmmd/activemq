package com.baizhi.controller;

import com.baizhi.service.ConsumerService;
import com.baizhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    ProductService productService;
    @Autowired
    ConsumerService consumerService;

    @RequestMapping("send")
    public void send(String msg) {
        productService.send(msg);
    }

    @RequestMapping("receive")
    public String receive() {
        String receive = consumerService.receive();
        return receive;
    }


}
