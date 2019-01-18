package com.baizhi.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class Product {
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        Destination destination = new ActiveMQQueue("springboot-queue");
        this.jmsTemplate.convertAndSend(destination, message);
    }

}
