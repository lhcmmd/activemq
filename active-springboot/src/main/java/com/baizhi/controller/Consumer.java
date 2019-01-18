package com.baizhi.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Consumer {
    @JmsListener(destination = "springboot-queue")
    public void receiveQueue(TextMessage msg) {
        try {
            System.out.println(msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
