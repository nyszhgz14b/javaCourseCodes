package com.example.task06.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MsgConsumer {
    private static final String url ="tcp://127.0.0.1:61616";
    private static final String topicName = "activemq-topic";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(topicName);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("recive message "+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        connection.close();
    }
}
