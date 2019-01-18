package com.baizhi.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;

import javax.jms.*;

public class TestActiveMqJava {
    @Test
    public void testProducter() throws JMSException {
        //1.创建工厂
        String brokeUrl = "tcp://192.168.194.144:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
//        2创建连接
        Connection connection = connectionFactory.createConnection();
      connection.start();
//        3创建会话  第一个参数代表第二个参数是否开启事务，参数2 自动发送回执
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//        4创建生产者
        //目的地必须是队列
        Destination destination = new ActiveMQQueue("javaQueue");
        MessageProducer producer = session.createProducer(destination);
        //5创建消息
        TextMessage message = session.createTextMessage();
        message.setText("hello amq");
//      6生产者发消息
        producer.send(message);
//         提交
        session.commit();
//        8.关闭
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testConsumer() throws JMSException {
        //1.创建工厂
        String brokeUrl = "tcp://192.168.194.144:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
//        2创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
//        3创建会话  第一个参数代表第二个参数是否开启事务，参数2 自动发送回执
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//        4创建消费者
        Destination destination = new ActiveMQQueue("javaQueue");
        MessageConsumer consumer = session.createConsumer(destination);
        //消费者获取消息
        Message message = consumer.receive();
        TextMessage textMessage = (TextMessage) message;
        String text = textMessage.getText();
        System.out.println(text);


        session.commit();
//        8.关闭
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testPub() throws JMSException {
        //1.创建工厂
        String brokeUrl = "tcp://192.168.194.144:61616";
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeUrl);
        //创建toptic连接
        TopicConnection topicConnection = connectionFactory.createTopicConnection();
        topicConnection.start();
        TopicSession topicSession = topicConnection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = new ActiveMQTopic("javaTopic");
        TopicPublisher topicPublisher = topicSession.createPublisher(topic);
        TextMessage textMessage = topicSession.createTextMessage();
        textMessage.setText("hello publisher");
        topicPublisher.send(textMessage);
        topicSession.commit();
        topicPublisher.close();
        topicSession.close();
        topicConnection.close();
    }
    @Test
    public  void testSub() throws JMSException {
        String brokeURl = "tcp://192.168.194.144:61616";
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeURl);
//        2.创建连接
        TopicConnection topicConnection = connectionFactory.createTopicConnection();
        topicConnection.start();
        TopicSession topicSession = topicConnection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = new ActiveMQTopic("javaTopic");
        TopicSubscriber subscriber = topicSession.createSubscriber(topic);
        while (true) {
            Message message = subscriber.receive();
            TextMessage textMessage = (TextMessage) message;
            if (textMessage != null) {
                System.out.println("订阅者1------"+textMessage.getText());
                topicSession.commit();
            } else {
                break;
            }
        }
    }
    @Test
    public  void testSub2() throws JMSException {
        String brokeURl = "tcp://192.168.194.144:61616";
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokeURl);
//        2.创建连接
        TopicConnection topicConnection = connectionFactory.createTopicConnection();
        topicConnection.start();
        TopicSession topicSession = topicConnection.createTopicSession(true,Session.AUTO_ACKNOWLEDGE);

        Topic topic = new ActiveMQTopic("javaTopic");
        TopicSubscriber subscriber = topicSession.createSubscriber(topic);
        while (true) {
            Message message = subscriber.receive();
            TextMessage textMessage = (TextMessage) message;
            if (textMessage != null) {
                System.out.println("订阅者2------"+textMessage.getText());
                topicSession.commit();
            } else {
                break;
            }
        }
    }

}
