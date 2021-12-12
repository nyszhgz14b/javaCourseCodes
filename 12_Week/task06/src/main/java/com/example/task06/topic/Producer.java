package com.example.task06.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    private static final String url ="tcp://127.0.0.1:61616";
    private static final String topicName = "activemq-topic";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(topicName);
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 1000; i++) {
            TextMessage textMessage = session.createTextMessage("topic send " + i);
            producer.send(textMessage);
            System.out.println("send message to topic"+textMessage.getText());
        }
    }
}
