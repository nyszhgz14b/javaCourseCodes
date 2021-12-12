package com.example.task06.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    private static final String url ="tcp://127.0.0.1:61616";
    private static final String queueName = "activemq-queue";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queueName);
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 1000; i++) {
            TextMessage message = session.createTextMessage("request test " + i);
            producer.send(message);
        }
        connection.close();
    }
}
