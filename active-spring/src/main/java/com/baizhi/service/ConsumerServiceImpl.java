package com.baizhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    JmsTemplate jmsTemplate;

    public String receive() {

        String text = null;
        try {
            TextMessage message = (TextMessage) jmsTemplate.receive();
            text = message.getText();
            System.out.println("this is consumer receive the text is------>" + text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return text;
    }
}
