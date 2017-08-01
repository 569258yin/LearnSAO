package com.kevinyin.learnsao.activityMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by kevinyin on 2017/8/1.
 */
public class Sender {
    public static final int SEND_NUMBER = 5;

    public static void main(String[] args) {
        //连接工厂
        ConnectionFactory connectionFactory;
        //Provider 的连接
        Connection connection = null;

        Session session;
        //消息发送者
        Destination destination;
        MessageProducer producer;

        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER
            ,ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://192.168.137.155:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            //获取操作连接
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            //得到消息的生产者
            producer = session.createProducer(destination);
            //设置不持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            sendMessage(session,producer);
            session.commit();
        }catch (Exception e){
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }

    private static void sendMessage(Session session, MessageProducer producer) throws JMSException {
        for (int i = 0; i < SEND_NUMBER; i++) {
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" + i);
            System.out.println("发送消息：" + message);
            producer.send(message);
        }
    }
}
