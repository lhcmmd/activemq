package com.baizhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class ProductController {

    @Autowired
    Product product;

    @RequestMapping("send")
    public void send(String msg) {
        product.sendMessage(msg);
    }

}
